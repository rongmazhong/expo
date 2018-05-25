package com.sha.expo;

import org.springframework.util.StringUtils;

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
	public static String readPropertiesFile(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String WORLDEXPOTIME = df.format(new Date());
		try {
			Properties props = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "\\time.properties"));
			props.load(in);
			String date = (String) props.get("WORLDEXPOTIME");
			if (StringUtils.isEmpty(date)){
			    date = WORLDEXPOTIME;
            }
            in.close();
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			//设置日期格式
			return WORLDEXPOTIME;
		}finally {

        }
	}

	/**
	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 *
	 * @param value 键值
	 */
	public static String writePropertiesFile(String value) throws IOException {
	    final String OK = "ok";
	    final String ERROR = "error";
		Properties properties = new Properties();
		File file = new File(System.getProperty("user.dir") + "\\time.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            properties.load(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            properties.setProperty("WORLDEXPOTIME",value);
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(new OutputStreamWriter(fos), "update");
            fos.close();
            fis.close();
            return OK;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return ERROR;
	}
}
