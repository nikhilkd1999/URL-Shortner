package com.nikhil.xurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nikhil.xurl.dao.IUrlMapRepository;
import com.nikhil.xurl.entities.UrlMap;

@SpringBootApplication
public class XurlApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx =  SpringApplication.run(XurlApplication.class, args);
		 
		IUrlMapRepository urlRepo =  ctx.getBean(IUrlMapRepository.class);
		 
		UrlMap um = new UrlMap("https//long", "https//short");
		 
		urlRepo.save(um);
		 
	}

}
