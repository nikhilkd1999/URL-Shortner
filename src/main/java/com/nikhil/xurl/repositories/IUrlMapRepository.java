package com.nikhil.xurl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikhil.xurl.entities.UrlMap;

@Repository
public interface IUrlMapRepository extends MongoRepository<UrlMap, String> {
	@Query("{ shortUrlHash : ?0 }")
	UrlMap findByShortUrlHash(String shortUrlHash);
}