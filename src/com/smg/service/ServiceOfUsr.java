package com.smg.service;

import com.smg.pojo.UserInfo;

public interface ServiceOfUsr {
	
	public UserInfo login(UserInfo user);
	
	public UserInfo checkRegisterName(String registerName);

}
