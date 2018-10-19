package com.smg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil pu = new PropertiesUtil();
	private Properties ps;
	private PropertiesUtil() {
		
		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("JDBC.properties");
		ps = new Properties();
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static PropertiesUtil init() {
		return pu;
	}
	public String getValue(String key) {
		return ps.getProperty(key);
	}

}
