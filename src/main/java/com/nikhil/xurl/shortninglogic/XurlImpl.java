package com.nikhil.xurl.shortninglogic;

import java.util.HashMap;
import java.util.Map;

public class XurlImpl implements IXurl {
    
    private Map<String, String> shortToLong;
    private Map<String, String> longToShort;
    private Map<String, Integer> longHitCount;
    private final static String PREFIX = "http://short.url/";
    static private Double NUM = 0.0;
    
    
    public XurlImpl() {
        shortToLong = new HashMap<>();
        longToShort = new HashMap<>();
        longHitCount = new HashMap<>();
    }

    private String generateShortUrl(String longUrl) {
        ++NUM;
        String hash = MD5Hash.getMd5(longUrl + NUM).substring(0,9);
        while(shortToLong.containsKey(PREFIX + hash))
        { 
            hash = MD5Hash.getMd5(longUrl + NUM).substring(0,9);
        }

        return PREFIX + hash;
    }

    @Override
    public String registerNewUrl(String longUrl) {

        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }

        String shortUrl = generateShortUrl(longUrl);

        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);

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
    public String getUrl(String shortUrl) {

        if (shortToLong.containsKey(shortUrl)) {
            String longUrl = shortToLong.get(shortUrl);
            longHitCount.put(longUrl, longHitCount.getOrDefault(longUrl, 0) + 1);
        }

        return shortToLong.getOrDefault(shortUrl, null);
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
