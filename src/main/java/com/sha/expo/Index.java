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
	@RequestMapping("/test")
	public ModelAndView test(){
		return new ModelAndView("test");
	}

    @RequestMapping("/full")
    public ModelAndView full(){
        return new ModelAndView("full");
    }
}
