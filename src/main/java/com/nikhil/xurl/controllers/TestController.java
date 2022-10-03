package com.nikhil.xurl.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.xurl.entities.UrlMap;
import com.nikhil.xurl.shortninglogic.XurlImpl;
import com.nikhil.xurl.utils.XurlConstants;

@RestController
public class TestController {

	// @GetMapping("/")
	// @ResponseBody
	// public String getResponce(HttpServletResponse httpServletResponse)
	// {
	// httpServletResponse.setHeader("Location",
	// "https://github.com/nikhilkd1999/URL-Shortner");
	// httpServletResponse.setStatus(302);
	// return null;
	// }

	@Autowired
	private XurlImpl xurlImpl;

	@GetMapping("/")
	@ResponseBody
	public String getHome() {
		return "Welcome";
	}

	@GetMapping("/{shortUrl}")
	@ResponseBody
	public ResponseEntity<Object> redirectToLongUrl(@PathVariable("shortUrl") String shortUrl)
			throws URISyntaxException {

		String longUrl = xurlImpl.getLongUrl(shortUrl);

		if (longUrl == null) {
			longUrl = XurlConstants.URL_PREFIX;
		}

		URI redirectUrl = new URI(longUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUrl);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

	@PostMapping("/register")
	public ResponseEntity<Object> registerSpecifiedUrl(@RequestBody UrlMap mapping) {
		String longUrl = mapping.getLongUrl();
		String shortUrl = mapping.getShortUrlHash();
		if (shortUrl == null) {
			return xurlImpl.registerNewUrl(longUrl);
		}
		return xurlImpl.registerNewUrl(longUrl, shortUrl);
	}

	@DeleteMapping("/unregister")
	public ResponseEntity<Object> deleteUrl(@RequestBody UrlMap urlMap) {
		String longUrl = urlMap.getLongUrl();
		return xurlImpl.delete(longUrl);
	}

}
