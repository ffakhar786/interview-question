package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.QueryStringService;

@EnableScheduling
@Component
@Configuration
public class QueryStringControllerScheduler {
	@Autowired
	private QueryStringService queryStringService;
	
	@Scheduled(cron = "3 * * * * ?")
	public void execute() {
		System.out.println("schedule is running .......");
		queryStringService.runScheduler();
	}
}
