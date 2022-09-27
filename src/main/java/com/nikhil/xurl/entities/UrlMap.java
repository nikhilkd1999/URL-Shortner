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
	@Builder.Default
	private Integer hitCount = 1;

	public UrlMap(String longUrl, String shortUrlHash, Integer hitCount) {
		this.longUrl = longUrl;
		this.shortUrlHash = shortUrlHash;
		this.hitCount = hitCount;
	}

	public UrlMap() {
		super();
	}
}
