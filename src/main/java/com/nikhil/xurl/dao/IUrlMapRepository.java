package com.nikhil.xurl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikhil.xurl.entities.UrlMap;

@Repository
public interface IUrlMapRepository extends JpaRepository<UrlMap, String> {

	// public List<UrlMap> findByLongUrl(String longUrl);
	// @Query("SELECT * FROM url_map WHERE short_url = : v")
	public List<UrlMap> findByShortUrlHash(@Param("v") String longUrl);
}