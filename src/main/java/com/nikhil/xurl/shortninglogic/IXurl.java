package com.nikhil.xurl.shortninglogic;

import org.springframework.http.ResponseEntity;

public interface IXurl {

	/**
	 * 
	 * If longUrl already has a corresponding shortUrl, return that shortUrl If
	 * longUrl is new, create a new shortUrl for the longUrl and return it
	 * 
	 * @param longUrl
	 * @return
	 */
	ResponseEntity<Object> registerNewUrl(String longUrl);

	/**
	 * 
	 * If shortUrl is already present, return null Else, register the specified
	 * shortUrl for the given longUrl Note: You don't need to validate if longUrl is
	 * already present, assume it is always new i.e. it hasn't been seen before
	 * 
	 * @param longUrl
	 * @param shortUrl
	 * @return
	 */
	ResponseEntity<Object> registerNewUrl(String longUrl, String shortUrl);

	/**
	 * 
	 * If shortUrl doesn't have a corresponding longUrl, return null Else, return
	 * the corresponding longUrl
	 * 
	 * @param shortUrl
	 * @return
	 */
	String getLongUrl(String shortUrl);

	/**
	 * Return the number of times the longUrl has been looked up using getUrl()
	 * 
	 * @param longUrl
	 * @return
	 */
	Integer getHitCount(String longUrl);

	/**
	 * Delete the record corresponding to the given longUrl 
	 * 
	 * @param longUrl
	 * @return
	 */
	ResponseEntity<Object> delete(String longUrl);

}