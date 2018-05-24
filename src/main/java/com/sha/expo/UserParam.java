package com.sha.expo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author:rong
 * Description: 获取参数
 * Data: Create in 下午 8:57 5.24/024
 * Package: com.sha.expo
 */
public class UserParam {

	// 获取properties文件属性值
	public static String readPropertiesFile() throws FileNotFoundException, IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		String WORLDEXPOTIME = df.format(new Date());
		try {
			Properties props = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream("D:\\expo\\src\\main\\resources\\time.properties"));
			props.load(in);
			String date = (String) props.get("WORLDEXPOTIME");
			System.out.println(date);

			return date;
		} catch (Exception e) {
			e.printStackTrace();
			//设置日期格式
			return WORLDEXPOTIME;
		}
	}

	/**
	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 *
	 * @param value 键值
	 */
	public static void writePropertiesFile(String value) throws IOException {
		Properties properties = new Properties();
		File file = new File("D:\\expo\\src\\main\\resources\\time.properties");
		FileInputStream fis = new FileInputStream(file);
		properties.load(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		properties.setProperty("WORLDEXPOTIME",value);
		FileOutputStream fos = new FileOutputStream(file);
		properties.store(new OutputStreamWriter(fos), "update");
		fos.close();
		fis.close();
	}
}
