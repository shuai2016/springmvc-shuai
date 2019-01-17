package xin.yangshuai.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xin.yangshuai.springmvc.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * SpringMVCTest
 *
 * @author shuai
 * @date 2019/1/15
 */
@Controller
//@SessionAttributes(value = {"user"}, types = {Date.class})
public class SpringMVCTest {
	public static final String SUCCESS = "success";

	@RequestMapping("testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}

	@RequestMapping("testForward")
	public String testForward(){
		System.out.println("testForward");
		return "forward:/index.jsp";
	}

	@RequestMapping("testView")
	public String testView(){
		System.out.println("testView");
		return "helloView";
	}

	//@ModelAttribute
	public void getUser(@RequestParam("id") Integer id, Map<String, Object> map) {
		System.out.println("getUser");
		User bb = null;
		if (id != null) {
			bb = new User(2, "BB", "123", 20, "a@aa.com");
			map.put("user", bb);
		}
		System.out.println(bb);
	}

	@RequestMapping(value = "/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("testModelAttribute");
		System.out.println(user);
		return SUCCESS;
	}

	@RequestMapping(value = "/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		System.out.println("testSessionAttributes");
		map.put("time", new Date());
		map.put("user", new User(1, "A", "123456", 18, "a@aa.com"));
		return SUCCESS;
	}

	@RequestMapping(value = "/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println("testMap");
		map.put("time", new Date());
		return SUCCESS;
	}

	@RequestMapping(value = "/testModelAndView")
	public ModelAndView testModelAndView() {
		System.out.println("testModelAndView");
		ModelAndView modelAndView = new ModelAndView(SUCCESS);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}

	@RequestMapping(value = "/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("testServletAPI");
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		return SUCCESS;
	}

	@RequestMapping(value = "/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo,user : " + user);
		return SUCCESS;
	}

	@RequestMapping(value = "/testCookieValue")
	public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
		System.out.println("testCookieValue,sessionId : " + sessionId);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader,Accept-Language : " + al);
		return SUCCESS;
	}


	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un
			, @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		System.out.println("testRequestParam,username : " + un + " , age : " + age);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRestGet(@PathVariable("id") Integer id) {
		System.out.println("TestRest GET : " + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testRestPost() {
		System.out.println("TestRest POST");
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id) {
		System.out.println("TestRest DELETE : " + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id) {
		System.out.println("TestRest PUT : " + id);
		return SUCCESS;
	}

	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable : " + id);
		return SUCCESS;
	}


	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		return SUCCESS;
	}

	@RequestMapping(value = "testParamsAndHeaders"
			, params = {"username", "age!=10"}
			, headers = {"Accept-Language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"})
	public String testParamsAndHeaders() {
		return SUCCESS;
	}
}
