package com.ems.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityService{
	
	static BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static String getEncodedPassword(String password) {
		if(bCryptPasswordEncoder==null) {
			bCryptPasswordEncoder=new BCryptPasswordEncoder();
		}
		return bCryptPasswordEncoder.encode(password);
	}

}
