package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	Logger logger = LogManager.getLogger(QueryStringControllerScheduler.class);
	
	@Autowired
	private QueryStringService queryStringService;
	
	//@Scheduled(cron = "0 30 * * * ?")
	@Scheduled(fixedDelay = 1000 * 60 * 3) 
	public void execute() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String runDateformate = df.format(new Date());
		logger.info("schedule is running ......." + runDateformate);
		int numberOfRecordsDeleted = queryStringService.runScheduler();
		logger.info("numberOfRecordsDeleted = " + numberOfRecordsDeleted);
	}
}
