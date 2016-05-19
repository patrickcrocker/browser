package org.cloudfoundry.demo.browser.svc;

import org.cloudfoundry.demo.browser.model.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("people-service")
public interface PeopleClient {

	@RequestMapping(method = RequestMethod.GET, value = "/people")
	PagedResources<Person> getPeople();
	
}
