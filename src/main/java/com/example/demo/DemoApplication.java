package com.example.demo;

import java.util.Arrays;
import java.util.concurrent.Executor;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.event.MyEvent;
import com.example.producer.EventPublisher;


/*
 * 
 * What've done in jersey
 * 1) Registered Jersey servlet with spring. 
 * 2) Defined the packages to scan
 * 3) EXtracted info from pathParam
 * 4) pathParamConverterProvider and pathParamConverter
 * 5) MessageBodyWriter
 * 6) CustomMediaType
 * 6) Filters to implement cross cutting concerns. 
 * 		EX:- i) Logging before and after request/response.
 * 			ii) User request authentication using Basic Authentication
 * 			iii) Exception handling
 * 			iv) Ordering of filters using priority
 * 7) Interceptors 
 * 
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example.controller"})
@EnableJpaRepositories("com.example.repositories")
@EntityScan(basePackages={"com.example.model"})
public class DemoApplication {

	@EventListener
	public void MyEventListener1(MyEvent e) {
		System.out.println("Event consumed for initiator by MyEventListener1 " + e.getEventInitiator());
	}
	
	@EventListener
	public void MyEventListener2(MyEvent e) {
		System.out.println("Event consumed for initiator by MyEventListener2" + e.getEventInitiator());
	}
	
	@Bean
	public EventPublisher EventPublisher() {
		return new EventPublisher();
	}
	
	@Bean
	public ServletRegistrationBean jerseyServlet() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/jersey/*");
	    // our rest resources will be available in the path /rest/*
	    registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
	    return registration;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//		String[] initiatorNames = {"Ramesh", "Hema", "Harish", "Shobhna", "Jeeva"};
//		
//		Arrays.stream(initiatorNames).forEach((name) -> { 
//			final String initiatorName = name;
//			System.out.println("initiator is " + name);
//			new Thread(()->((EventPublisher)context.getBean(EventPublisher.class)).publish(new MyEvent(initiatorName))).start();
//		});
			
	}
	
}

class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	//Spring filter to provide a bridge between JAX-RS and Spring request attributes.
        register(RequestContextFilter.class);
        packages("com.example.controller");
        //register(LoggingFilter.class);
    }
}

//@RestController("/")
//class UserDetailController {
//
//	@Autowired
//	private UserDetailRepository repository;
//		
//	@RequestMapping(value="/userDetail/{userId}")
//	@ResponseBody
//	public UserDetail userDetails(@RequestParam int userId) {
//		
//		UserDetail userDetail = repository.findOne(userId);
//		
//		return userDetail;
//	}
//}
