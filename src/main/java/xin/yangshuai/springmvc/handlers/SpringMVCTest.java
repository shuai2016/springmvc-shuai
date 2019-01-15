package xin.yangshuai.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xin.yangshuai.springmvc.entities.User;

/**
 * SpringMVCTest
 *
 * @author shuai
 * @date 2019/1/15
 */
@Controller
public class SpringMVCTest {
	public static final String SUCCESS = "success";

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
