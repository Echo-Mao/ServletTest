package com.smg.pojo;

public class UserInfo {
	
	private String usrName;
	private String usrPwd;
	public UserInfo() {
		super();
	}
	public UserInfo(String usrName, String usrPwd) {
		super();
		this.usrName = usrName;
		this.usrPwd = usrPwd;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsrPwd() {
		return usrPwd;
	}
	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}
	@Override
	public String toString() {
		return "UserInfo [usrName=" + usrName + ", usrPwd=" + usrPwd + "]";
	}
}
