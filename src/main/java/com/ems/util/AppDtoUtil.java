package com.ems.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ems.dto.EmployeeDto;
import com.ems.dto.UserDto;
import com.ems.entity.Employee;
import com.ems.entity.User;

public class AppDtoUtil {

	public static Employee convertDtoToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setDepartment(dto.getDepartment());
		employee.setGender(dto.getGender());
		try {
			Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDateOfBirth());
			employee.setDateOfBirth(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
//		employee.setRole(dto.getRole());
		// employee.set
		return employee;
	}

	public static User convertDtoToEntity(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return user;
	}
}
