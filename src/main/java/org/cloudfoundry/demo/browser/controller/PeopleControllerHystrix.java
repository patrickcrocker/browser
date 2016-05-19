package org.cloudfoundry.demo.browser.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cloudfoundry.demo.browser.model.Person;
import org.cloudfoundry.demo.browser.svc.PeopleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@Profile("hystrix")
@EnableCircuitBreaker
public class PeopleControllerHystrix {

		
	@Autowired
	private PeopleClient peopleClient;
		
	private Logger logger = Logger.getLogger(PeopleControllerHystrix.class);
	
	public PeopleControllerHystrix() {
		logger.info("HYSTRIX ENABLED");
	}
		
	@HystrixCommand(fallbackMethod = "getAllPeopleFallback")
	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public List<Person> getAllPeople() {
		logger.info("fetching...");
		PagedResources<Person> resources = peopleClient.getPeople();
		logger.info("Fetched: " + resources);
		return new ArrayList<Person>(resources.getContent());
	}
	
	public List<Person> getAllPeopleFallback() {
		logger.info("Fallback to empty array");
		return new ArrayList<Person>();
	}

	
}
