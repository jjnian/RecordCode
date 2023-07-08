package cn.runningone.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Hello {

	@RequestMapping(value = "/hell")
	@ResponseBody
	public String test1(){
		return "hello springmvc";
	}

	@GetMapping("/hello/{year1}/{year2}")//@PathVariable("year1") Integer year,
	public String index(Integer year1){
		return "index";
	}

	@GetMapping("/user1")
	public String getUserAll(HttpServletRequest request){
		List result = new ArrayList<User>();
		result.add(new User("111"));
		request.setAttribute("userAll",result);
		return "index/index";
	}


	@GetMapping("/user2")
	public String getUserAll(Model model){
		List result = new ArrayList<User>();
		result.add(new User("222"));
		model.addAttribute("userAll",result);
		return "index/index";
	}


	@GetMapping("/user3")
	public ModelAndView getUserAll(ModelAndView modelAndView){
		List result = new ArrayList<User>();
		result.add(new User("333"));
		modelAndView.addObject("userAll",result);
		modelAndView.setViewName("index/index");
		return modelAndView;
	}

	@PostMapping("/user4/data")
	public String uploadRiskResourceData(@RequestParam(value = "text",required = false) String text){
		System.out.println(text);

		return "String";
	}

}
