package com.ems.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.UserDto;
import com.ems.service.AdminService;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@RequestMapping(path="api/admin")
@CrossOrigin(origins="*")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping(path="/user",consumes= {MediaType.APPLICATION_JSON_VALUE},produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode createUser(@RequestBody UserDto userDto) {
	   ObjectNode objectNode=adminService.createUser(userDto);
		return objectNode;
	}
	
	@PutMapping(path="/user",consumes= {MediaType.APPLICATION_JSON_VALUE},produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode updateUser(@RequestBody UserDto userDto) {
		   ObjectNode objectNode=adminService.updateUser(userDto);
			return objectNode;
		}
	
	@DeleteMapping(path="/user",consumes= {MediaType.APPLICATION_JSON_VALUE},produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode deleteUser(@RequestParam("userid") String userId) {
		return adminService.deleteUser(userId);
	}

}
