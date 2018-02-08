package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserDetail;
import com.example.repositories.UserDetailRepository;

@RestController
public class UserDetailController {

	@Autowired
	private UserDetailRepository repository;
		
	@RequestMapping(value="/userDetail")
	@ResponseBody
	public UserDetail userDetails() {
		
		UserDetail userDetail = repository.findOne(1);
		
		return userDetail;
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public void saveUser(@RequestBody(required=true) UserDetail userDetail) {
		repository.save(userDetail);
	}
	
	@RequestMapping(value="/findUser/address/{add}")
	@ResponseBody
	public List<UserDetail> findUserByAddress(@PathVariable("add") String address) {
		return repository.findByAddressContaining(address);
	}
}
