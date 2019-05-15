package com.ems.dao;

import org.springframework.data.repository.CrudRepository;

import com.ems.entity.Employee;

public interface RegistrationDAO extends CrudRepository<Employee,Integer>{

}
