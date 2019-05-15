package com.ems.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.RegistrationDAO;
import com.ems.dto.EmployeeDto;
import com.ems.entity.Employee;
import com.ems.util.AppDtoUtil;
import com.ems.util.AppUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RegistrationService {
	
	@Autowired
	RegistrationDAO registrationDAO;
	
	@Autowired
	EntityManager entityManager;
	
	
    public ObjectNode regsiter(EmployeeDto dto){
    	this.addEmployee(dto);
    	ObjectNode objectNode = AppUtil.getObjectNodeInstance();
    	objectNode.put("success", "Employee Registered successfully");
		return objectNode;
    }
    
    
    public void addEmployee(EmployeeDto dto){

		Employee employee = AppDtoUtil.convertDtoToEntity(dto);
    	registrationDAO.save(employee);
    }


	public ObjectNode deleteEmployee(EmployeeDto employee) {
		ObjectNode objectNode = AppUtil.getObjectNodeInstance();
		try {
			Employee entity = AppDtoUtil.convertDtoToEntity(employee);
			registrationDAO.delete(entity);
			objectNode.put("success", "Employee removed successfullly.");
		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return null;
	}


	public ObjectNode updateEmployee(EmployeeDto employee) {
		ObjectNode objectNode = AppUtil.getObjectNodeInstance();
		try {
			Employee entity = AppDtoUtil.convertDtoToEntity(employee);
			entityManager.merge(entity);
			objectNode.put("success", "Employee removed successfullly.");
		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return objectNode;
	}


	public ObjectNode getEmployee(String firstName) {
		ObjectNode objectNode = AppUtil.getObjectNodeInstance();
		try {
			if(null!=firstName) {
				firstName=firstName.toUpperCase();
			}
			List<Employee> employeeList = entityManager.createQuery("select e from Employee e where upper(e.firstName) like :firstName")
					.setParameter("firstName", firstName+"%").getResultList();
			String parsedEmployeeListStr = AppUtil.parseEntityListToString(employeeList);
			objectNode.put("employeeList", parsedEmployeeListStr);
			
		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return objectNode;
	}
    
    

}
