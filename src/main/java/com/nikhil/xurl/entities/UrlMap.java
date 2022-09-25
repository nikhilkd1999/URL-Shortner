package com.nikhil.xurl.entities;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class UrlMap {

	@Id
	private String longUrl;
	private String shortUrl;
	
	public UrlMap(String longUrl, String shortUrl) {
		super();
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
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

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
}
