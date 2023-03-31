package com.nikhil.xurl.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikhil.xurl.services.IURLMapService;
import com.nikhil.xurl.utils.XurlConstants;

@Controller
public class URLController {

	@Autowired
	private IURLMapService urlMapService;

	@GetMapping("/")
	public String getHome() {
		return "home_page";
	}

	@ResponseBody
	@GetMapping("/{shortUrl}")
	public ResponseEntity<Object> redirectToLongUrl(@PathVariable("shortUrl") String shortUrl)
			throws URISyntaxException {

		String longUrl = urlMapService.getLongUrl(shortUrl);

		if (longUrl == null || longUrl == "") {
			longUrl = XurlConstants.URL_PREFIX;
		}

		URI redirectUrl = new URI(longUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUrl);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

	// @ResponseBody
	@PostMapping("/register")
	public String registerSpecifiedUrl(@RequestParam String longUrl, HttpSession session) {

		final String shortUrl = urlMapService.registerUrl(longUrl);

		session.setAttribute("shortUrl", shortUrl);
		session.setAttribute("longUrl", longUrl);

		return "redirect:/";
	}

}
