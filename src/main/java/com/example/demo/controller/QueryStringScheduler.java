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

/** Represents a QueryString Controller.
 * @author Fakhar Mumtaz
 * @version 1.0
 * @since 1.0
*/
@EnableScheduling
@Component
@Configuration
public class QueryStringScheduler {
	Logger logger = LogManager.getLogger(QueryStringScheduler.class);
	
	@Autowired
	private QueryStringService queryStringService;
	
	/**  executes the scheduler at the specified number of milliseconds
	 * @return  it returns void 
	*/
	@Scheduled(fixedDelay = 1001 * 60 * 30) 
	public void execute() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String runDateformate = df.format(new Date());
		logger.info("schedule is running ......." + runDateformate);
		Integer numberOfRecordsDeleted = queryStringService.runScheduler();
		logger.info("numberOfRecordsDeleted = " + numberOfRecordsDeleted);
	}
}
