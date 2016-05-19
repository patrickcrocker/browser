package org.cloudfoundry.demo.browser.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cloudfoundry.demo.browser.model.Person;
import org.cloudfoundry.demo.browser.svc.PeopleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {
		
	@Autowired
    private PeopleClient peopleClient;
	
	private Logger logger = Logger.getLogger(PeopleController.class);
	
	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public List<Person> getAllPeople() {
	    PagedResources<Person> resources = peopleClient.getPeople();
	    logger.info("Fetched: " + resources);
		return new ArrayList<Person>(resources.getContent());
	}

}
