package xin.yangshuai.springmvc.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xin.yangshuai.springmvc.entities.User;
import xin.yangshuai.springmvc.exception.UserNameNotMatchPasswordException;
import xin.yangshuai.springmvc.utils.DataUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
	public static final String ERROR = "error";

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping("testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(int i) {
		String [] vals = new String[10];
		System.out.println(vals[i]);
		System.out.println("testSimpleMappingExceptionResolver");
		return SUCCESS;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "测试")
	@RequestMapping("testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(int i) {
		System.out.println("testResponseStatusExceptionResolver");
		if(i == 13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("not 13");
		return SUCCESS;
	}

	@RequestMapping("testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(int i) {
		System.out.println("testExceptionHandlerExceptionResolver");
		System.out.println("result : " + (10 / i));
		return SUCCESS;
	}

	@RequestMapping("testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) {
		System.out.println("desc : " + desc);
		System.out.println(file.getOriginalFilename());
		return SUCCESS;
	}

	@RequestMapping("testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpServletRequest request) throws IOException {

		InputStream in = request.getServletContext().getResourceAsStream("/file/wallhaven-680530.jpg");
		byte[] body = new byte[in.available()];
		in.read(body);

		String fileName = "图片.jpg";
		/**
		 * 如果是火狐,解决火狐中文名乱码问题
		 */
		if (DataUtils.getBrowser(request).equals("FF")) {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=" + fileName);

		HttpStatus status = HttpStatus.OK;

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, headers, status);
		return responseEntity;
	}

	@RequestMapping("i18n")
	public String testI18n(Locale locale) {
		String username = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(username);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("testJson")

	public Object testJson() {
		Map<String, Object> map = new HashMap<>();
		User user = new User(1, "A", "123456", 18, "a@aa.com");
		map.put("user", user);
		System.out.println("testJson");
		return map;
	}

	/**
	 * 不自动绑定对象中的roleSet 属性，另行处理
	 *
	 * @param dataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("roleSet");
	}

	@RequestMapping("testConversionServiceConverter")
	public String testConverter(@RequestParam("user") User user) {
		System.out.println(user);
		return SUCCESS;
	}

	@RequestMapping("testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}

	@RequestMapping("testForward")
	public String testForward() {
		System.out.println("testForward");
		return "forward:/index.jsp";
	}

	@RequestMapping("testView")
	public String testView() {
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
