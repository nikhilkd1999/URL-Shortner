package com.nikhil.xurl.shortninglogic;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nikhil.xurl.dao.IUrlMapRepository;
import com.nikhil.xurl.entities.UrlMap;
import com.nikhil.xurl.utils.XurlConstants;

@Service
public class XurlImpl implements IXurl {

	static private Random SALT = new Random();

	@Autowired
	private IUrlMapRepository urlRepo;

	/**
	 * Generates hash value for <code>longURL</code> which can be used as a <code>shortUrl</code>.
	 * 
	 * @param longUrl
	 * 
	 */
	private String generateShortUrlHash(String longUrl) {

		String hash = MD5Hash.getMd5(longUrl + SALT.nextGaussian()).substring(0, XurlConstants.SHORT_URL_HASH_LENGTH);
		while (getIfExistShort(hash) != null) {
			hash = MD5Hash.getMd5(longUrl + SALT.nextGaussian()).substring(0, XurlConstants.SHORT_URL_HASH_LENGTH);
		}

		return hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Object> registerNewUrl(String longUrl) {

		String shortUrlHash = "";

		UrlMap urlMap = getIfExistLong(longUrl);

		if (urlMap != null) {
			shortUrlHash = urlMap.getShortUrlHash();
		} else {
			shortUrlHash = generateShortUrlHash(longUrl);
			urlMap = UrlMap.builder().longUrl(longUrl).shortUrlHash(shortUrlHash).build();
			urlRepo.save(urlMap);
		}
		return new ResponseEntity<Object>(getFormatedShortUrl(shortUrlHash), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Object> registerNewUrl(String longUrl, String shortUrl) {

		if(getIfExistShort(shortUrl)!=null){
			return null;
		}

		UrlMap urlMap = UrlMap.builder().longUrl(longUrl).shortUrlHash(shortUrl).build();
		urlRepo.save(urlMap);
		return new ResponseEntity<Object>("URL mapped successfully!", HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLongUrl(String shortUrlHash) {

		List<UrlMap> longUrls = urlRepo.findByShortUrlHash(shortUrlHash);

		if (longUrls.isEmpty()) {
			return null;
		} else {
			// Increment hit count by 1
			UrlMap urlMap = longUrls.get(0);
			urlMap.setHitCount(urlMap.getHitCount() + 1);
			urlRepo.save(urlMap);
			return urlMap.getLongUrl();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getHitCount(String longUrl) {
		return urlRepo.findById(longUrl).get().getHitCount();
	}

	/**
	 * Yet to implement
	 */
	@Override
	public String delete(String longUrl) {
		return null;
	}

	/**
	 * Returns {@link UrlMap} if the <code>shortUrl</code> exists in DB else returns
	 * <code>null</code>
	 * 
	 * @param shortUrl
	 * 
	 */
	private UrlMap getIfExistShort(String shortUrl) {
		List<UrlMap> urlMaps = urlRepo.findByShortUrlHash(shortUrl);
		if (urlMaps.isEmpty()) {
			return null;
		}
		return urlMaps.get(0);
	}

	/**
	 * Returns {@link UrlMap} if the <code>longUrl</code> exists in DB else returns
	 * <code>null</code>
	 * 
	 * @param longUrl
	 * 
	 */
	private UrlMap getIfExistLong(String longUrl) {

		Optional<UrlMap> opnl = urlRepo.findById(longUrl);

		if (opnl.isPresent()) {
			return opnl.get();
		}
		return null;
	}

	/**
	 * Returns concatenated String of URL_PREFIX and shortUrlHash
	 * 
	 * @param shortUrlHash
	 * 
	 * @return shortUrl
	 */
	private String getFormatedShortUrl(String shortUrlHash) {
		return XurlConstants.URL_PREFIX + shortUrlHash;
	}

}
