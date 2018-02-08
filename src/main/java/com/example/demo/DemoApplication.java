package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example.controller"})
@EnableJpaRepositories("com.example.repositories")
@EntityScan(basePackages={"com.example.model"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
