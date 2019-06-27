/**
 * 
 */
package com.greet;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shar939
 *
 */
@RestController
public class HelloController {
	
	@Autowired
	private RestTemplate client;
	
	private Logger log = LoggerFactory.getLogger(GreetServiceApplication.class);

	@GetMapping(path = "/sayHelloin/{language}")
	public String sayHello(@PathVariable(name = "language") String language) {
		log.debug("In sayHello "+language);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		log.debug("Invoking the API");
		String externalForexURI = "http://localhost:8081/hello/"+language;

		String helloInLanguage = client.exchange(externalForexURI, HttpMethod.GET, entity, String.class).getBody();
		log.debug("Response "+helloInLanguage);
		return helloInLanguage;
	}
}
