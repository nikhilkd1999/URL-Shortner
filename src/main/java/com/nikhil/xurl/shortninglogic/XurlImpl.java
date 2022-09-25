package com.nikhil.xurl.shortninglogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.xurl.dao.IUrlMapRepository;
import com.nikhil.xurl.entities.UrlMap;

@Service
public class XurlImpl implements IXurl {

    private Map<String, String> shortToLong;
    private Map<String, String> longToShort;
    private Map<String, Integer> longHitCount;
    // private final static String PREFIX = "http://short.url/";
    
    private final static String PREFIX = "http://localhost:9099/";
    static private Double NUM = 0.0;

    @Autowired
    private IUrlMapRepository urlRepo;

    public XurlImpl() {
        shortToLong = new HashMap<>();
        longToShort = new HashMap<>();
        longHitCount = new HashMap<>();
    }

    private String generateShortUrl(String longUrl) {
        ++NUM;
        String hash = MD5Hash.getMd5(longUrl + NUM).substring(0, 9);
        while (shortToLong.containsKey(PREFIX + hash)) {
            hash = MD5Hash.getMd5(longUrl + NUM).substring(0, 9);
        }

        return PREFIX + hash;
    }

    @Override
    public String registerNewUrl(String longUrl) {

        String shortUrl = null;

        Optional<UrlMap> op = urlRepo.findById(longUrl);
        UrlMap urlMap;
        if(op.isPresent()) {
            urlMap = op.get();
        } else {
            urlMap = null;
        }

        if (urlMap != null) {
            return urlMap.getShortUrl();
        } else {
            shortUrl = generateShortUrl(longUrl);
            urlMap = new UrlMap(longUrl, shortUrl);
            urlRepo.save(urlMap);
        }
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {

        if (shortToLong.containsKey(shortUrl)) {
            return null;
        }

        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);

        return shortUrl;
    }

    @Override
    public String getLongUrl(String shortUrl) {

        List<UrlMap> longUrls = urlRepo.findByShortUrl(PREFIX + shortUrl);

        if (longUrls.isEmpty()) {
            return null;
        } else {
            return longUrls.get(0).getLongUrl();
        }
    }

    @Override
    public Integer getHitCount(String longUrl) {

        return longHitCount.getOrDefault(longUrl, 0);
    }

    @Override
    public String delete(String longUrl) {

        if (longToShort.containsKey(longUrl)) {
            String shortURL = longToShort.get(longUrl);
            longToShort.remove(longUrl);
            shortToLong.remove(shortURL);
        }

        return null;
    }

}
