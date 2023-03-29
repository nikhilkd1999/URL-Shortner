package com.nikhil.xurl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.xurl.entities.UrlMap;
import com.nikhil.xurl.hash_algo.MD5Hash;
import com.nikhil.xurl.repositories.IUrlMapRepository;
import com.nikhil.xurl.utils.XurlConstants;

@Service
public class URLMapService implements IURLMapService {

    @Autowired
    private IUrlMapRepository urlMapRepository;

    @Override
    public String registerUrl(String longUrl) {

        Optional<UrlMap> longUrlOptional = urlMapRepository.findById(longUrl);

        if (longUrlOptional.isPresent()) {
            UrlMap urlMap = longUrlOptional.get();

            String shortUrlHash = urlMap.getShortUrlHash();

            String shortUrl = getFormatedShortUrl(shortUrlHash);

            return shortUrl;

        }

        String shortUrlHash = generateShortUrlHash(longUrl);

        UrlMap urlMap = UrlMap.builder().shortUrlHash(shortUrlHash).longUrl(longUrl).build();

        urlMapRepository.save(urlMap);

        String shortUrl = getFormatedShortUrl(shortUrlHash);

        return shortUrl;

    }

    @Override
    public String getLongUrl(String shortUrlHash)  {

        UrlMap urlMap = urlMapRepository.findByShortUrlHash(shortUrlHash);

        if (urlMap == null) {
            return "";
        }

        return urlMap.getLongUrl();

    }

    @Override
    public Integer getHitCount(String longUrl) {
        Optional<UrlMap> urlMapOptional = urlMapRepository.findById(longUrl);
        if (urlMapOptional.isPresent()) {
            return urlMapOptional.get().getHitCount();
        }
        return 0;
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

    

    /**
     * Generates hash value for <code>longURL</code> which can be used as a
     * <code>shortUrl</code>.
     * 
     * @param longUrl
     * 
     */
    private String generateShortUrlHash(String longUrl) {

        String hash = MD5Hash.getMd5(longUrl + SALT.nextGaussian()).substring(0, XurlConstants.SHORT_URL_HASH_LENGTH);
        while (urlMapRepository.findByShortUrlHash(hash) != null) {
            hash = MD5Hash.getMd5(longUrl + SALT.nextGaussian()).substring(0, XurlConstants.SHORT_URL_HASH_LENGTH);
        }

        return hash;
    }

}
