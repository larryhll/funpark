package com.myeden.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取类
 * 
 * @author Felix
 * 
 */
public class PropertiesDAO {

	/**
	 * 传入文件路径，以及要读的key值，返回key对应的values值
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */

	public static String readValue(String filePath, String key) {

			// 添加适配代码,如果这个值传空，则取默认的conf.properties位置
			if (null == filePath || filePath.equals("")) {
				try {
					filePath = CommonUtils_cjc.getFilePath();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("找不到application.properties");
				}
			} 

			try {
			Properties props = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			String value = props.getProperty(key);
//			System.out.println(key + ":" + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void writeValue(String filePath, String key, String value){
		// 添加适配代码,如果这个值传空，则取默认的conf.properties位置
					if (null == filePath || filePath.equals("")) {
						try {
							filePath = CommonUtils_cjc.getFilePath();
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("找不到conf.properties");
						}
					} 
		
		
					try {
						Properties props = new Properties();
						InputStream in = new BufferedInputStream(new FileInputStream(
								filePath));
						props.load(in);
						props.put(key, value);
						
						//String value = props.getProperty(key);
//						System.out.println(key + ":" + value);
						
					} catch (Exception e) {
						e.printStackTrace();
						
					}
					
		
	}
	

}
