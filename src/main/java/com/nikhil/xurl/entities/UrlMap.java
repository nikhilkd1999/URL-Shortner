package com.nikhil.xurl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class UrlMap {

	@Id
	private String longUrl;
	private String shortUrlHash;

	public UrlMap(String longUrl, String shortUrl) {
		super();
		this.longUrl = longUrl;
		this.shortUrlHash = shortUrl;
	}

	public UrlMap() {
		super();
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrlHash() {
		return shortUrlHash;
	}

	public void setShortUrlHash(String shortUrlHash) {
		this.shortUrlHash = shortUrlHash;
	}

}
