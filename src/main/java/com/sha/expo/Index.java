package com.sha.expo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author rongmazhong@outlook.com
 * @date Create in 下午 7:14 5.24/024
 */
@Controller
@RequestMapping
public class Index {

	/**
	 * 首页
	 * @return ModelAndView
	 * @author rongmazhong@outlook.com
	 * @date 5.24/024 下午 8:55
	 */
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView index(){
		return new ModelAndView("home");
	}
	@RequestMapping("/getTime")
	@ResponseBody
	public String getTime() {
		String date = null;
		try {
			date = UserParam.readPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return date;
	}
	@RequestMapping("/setTime")
	@ResponseBody
	public String setTime(String time){
		try {
			UserParam.writePropertiesFile(time);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}
	@RequestMapping("/test")
	public ModelAndView test(){
		return new ModelAndView("test");
	}
}
