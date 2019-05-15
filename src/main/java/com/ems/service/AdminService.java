package com.ems.service;

import java.util.Iterator;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.AdminDAO;
import com.ems.dto.EmployeeDto;
import com.ems.dto.UserDto;
import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.security.SecurityService;
import com.ems.util.AppDtoUtil;
import com.ems.util.AppUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author ChanDiCh
 *
 */
@Service
public class AdminService {

	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	EntityManager entityManager;

	/**
	 * @param employeeDto
	 * @return
	 */
	public ObjectNode createUser(UserDto userDto) {

		// Encryption
		String encodedPassword = SecurityService.getEncodedPassword(userDto.getPassword());
		userDto.setPassword(encodedPassword);

		ObjectNode objectNode = AppUtil.getObjectNodeInstance();
		User entity = AppDtoUtil.convertDtoToEntity(userDto);
		try {
			User user = adminDAO.save(entity);
			if (null != user) {
				objectNode.put("success", "User created successfully..");
			}

		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return objectNode;
	}

	public ObjectNode deleteUser(String userId) {
		ObjectNode objectNode=null;
		try {
			 objectNode = AppUtil.getObjectNodeInstance();
			User userDB = null;
			Iterable<User> users = adminDAO.findAll();
			Iterator<User> iterator = users.iterator();
			while (iterator.hasNext()) {
				User user = iterator.next();
				if (userId.equalsIgnoreCase(user.getUserName())) {
					userDB = user;
					break;
				}
			}

			adminDAO.delete(userDB);
			objectNode.put("success", "User removed successfully");
		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return objectNode;
	}

	public ObjectNode updateUser(UserDto userDto) {
		ObjectNode objectNode = AppUtil.getObjectNodeInstance();
		try {
			User entity = AppDtoUtil.convertDtoToEntity(userDto);
			entityManager.merge(entity);
			objectNode.put("sucess", "User updated successfully.");
		} catch (Exception e) {
			objectNode.put("fail", e.getMessage());
		}
		return objectNode;
	}

}
