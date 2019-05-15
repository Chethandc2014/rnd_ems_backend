package com.ems.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDto;
import com.ems.service.RegistrationService;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/register")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@RequestMapping(path = "/user", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode registerEmployee(@RequestBody EmployeeDto employee) {

		ObjectNode objectNode = registrationService.regsiter(employee);
		return objectNode;

	}
	
	@RequestMapping(path = "/user", method = RequestMethod.GET,produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode registerEmployee(@RequestParam String firstName ) {

		ObjectNode objectNode = registrationService.getEmployee(firstName);
		return objectNode;

	}
	

	@RequestMapping(path = "/user", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode updateEmployee(@RequestBody EmployeeDto employee) {

		ObjectNode objectNode = registrationService.updateEmployee(employee);
		return objectNode;

	}
	
	
	@RequestMapping(path = "/user", method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE },produces= { MediaType.APPLICATION_JSON_VALUE })
	public ObjectNode delteEmployee(@RequestBody EmployeeDto employee) {

		ObjectNode objectNode = registrationService.deleteEmployee(employee);
		return objectNode;

	}

	
	

}
