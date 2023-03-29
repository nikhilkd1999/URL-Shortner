package com.nikhil.xurl.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UrlMap {

	@Id
	private String longUrl;
	private String shortUrlHash;
	
	@Builder.Default
	private Integer hitCount = 1;

}
