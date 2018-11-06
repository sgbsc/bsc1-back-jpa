package com.sgbsc;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgbsc.article.Articles;

/*
 * This is a @Controller for two static webpages.
 * 
 * In Spring, a Controller is a class which is responsible for preparing 
 * a model Map with data to be displayed by the view as well as choosing 
 * the right view itself. It can also directly write into response stream 
 * by using @ResponseBody annotation and complete the request.
 * 
 * If we were writing a RESTful application, we'd use @RestController
 * which is a convenience controller meaning @Controller + @ResponseBody.
 * 
 * This application just returns two pages: 
 * 
 * index.html
 * - sent back automatically by the DispatcherServlet
 * - auto-configured by Spring Boot upon seeing @SpringBootApplication
 *   and Web starter in pom.xml. 
 * - It looks for an index.html, and css under the static resources folder.
 *   
 * hello-template.html
 * - Spring Boot notices thymeleaf starter in pom.xml and  
 * - Spring's RequestMappingHandlerMapping is used to maintain the mapping of the 
 *   request URI to the handler (which are the controller methods annotated with the
 *   @RequestMapping annotation). Once the handler is obtained, the DispatcherServlet 
 *   dispatches the request to the appropriate handler adapter.
 */

@SpringBootApplication
@Controller
public class Bsc1Application {
	
	//performs persistence operations between orm and database
	@Autowired
	private EntityManager em;

	@RequestMapping("/hello-template")
	public String helloTemplate() {
		return "hello-template";
	}
	
	@RequestMapping("/jpa-articles")
	public String articles(Map<String, Object> model){
		
		//get all the articles
		List<Articles> articles = em.createQuery("select a from Articles a").getResultList();
		
		//put the articles in the model so that we can access them in the Thymeleaf template
		model.put("articles", articles);
		
		return "jpa-articles";
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Bsc1Application.class, args);
	}
}
