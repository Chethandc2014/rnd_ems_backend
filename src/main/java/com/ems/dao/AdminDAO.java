package com.ems.dao;


import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Employee;
import com.ems.entity.User;

@Repository
public interface AdminDAO extends CrudRepository<User, UUID> {
	
}
