package com.sha.expo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
		date = UserParam.readPropertiesFile();
		return date;
	}
	/**
	 * 时间设置
	 * @param time 时间
	 * @return string
	 * @author rongmazhong@outlook.com
	 * @date 5.26/026 上午 11:31
	 */
	@RequestMapping(value = "/setTime",method = RequestMethod.POST)
	@ResponseBody
	public String setTime(@RequestBody Mytime time){
	    String stringTime = time.getCallTime();
		try {
			String r =  UserParam.writePropertiesFile(stringTime);
			if (r.equalsIgnoreCase("ok")){
				return "ok";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	/**
	 * 测试
	 * @return test.html
	 * @author rongmazhong@outlook.com
	 * @date 5.26/026 上午 11:31
	 */
	@RequestMapping("/test")
	public ModelAndView test(){
		return new ModelAndView("test");
	}

    @RequestMapping("/full")
    public ModelAndView full(){
        return new ModelAndView("full");
    }
}
