package com.nikhil.xurl.services;

import java.util.Random;

public interface IURLMapService {

    final Random SALT = new Random();

    
	/**
	 * 
	 * If longUrl already has a corresponding shortUrl, return that shortUrl If
	 * longUrl is new, create a new shortUrl for the longUrl and return it
	 * 
	 * @param longUrl
	 * @return shortUrl
	 */
	String registerUrl(String longUrl);


	/**
	 * 
	 * If shortUrl doesn't have a corresponding longUrl, return null Else, return
	 * the corresponding longUrl
	 * 
	 * @param shortUrl
	 * @return
	 */
	String getLongUrl(String shortUrlHash);

	/**
	 * Return the number of times the longUrl has been looked up using getUrl()
	 * 
	 * @param longUrl
	 * @return
	 */
	Integer getHitCount(String longUrl);

    
}
