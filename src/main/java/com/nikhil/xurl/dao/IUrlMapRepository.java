package com.nikhil.xurl.dao;

import org.springframework.data.repository.CrudRepository;

import com.nikhil.xurl.entities.UrlMap;


public interface IUrlMapRepository extends CrudRepository<UrlMap, String>{

}