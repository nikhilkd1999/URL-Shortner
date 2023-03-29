package com.nikhil.xurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nikhil.xurl.repositories.IUrlMapRepository;

@SpringBootApplication
public class XurlApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx =  SpringApplication.run(XurlApplication.class, args);
		 
		IUrlMapRepository urlRepo =  ctx.getBean(IUrlMapRepository.class);

		System.out.println(urlRepo);
		 
		// UrlMap um = UrlMap.builder().longUrl("https//long").shortUrlHash("https//short").build();
		 
		// urlRepo.save(um);
		 
	}

}
