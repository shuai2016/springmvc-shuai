package xin.yangshuai.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWorld
 *
 * @author shuai
 * @date 2019/1/14
 */
@Controller
public class HelloWorld {

	@RequestMapping("/helloworld")
	public String hello(){
		System.out.println("hello world");
		return "/success";
	}
}
