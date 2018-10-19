package com.smg.dao;

import com.smg.pojo.UserInfo;

public interface UsrDao {
	//登录
	public UserInfo login(UserInfo user);
	//检查重名
	public UserInfo checkRegisterName(String ragisterName);

}
