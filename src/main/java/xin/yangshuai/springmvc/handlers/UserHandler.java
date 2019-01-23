package xin.yangshuai.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xin.yangshuai.springmvc.entities.Address;
import xin.yangshuai.springmvc.entities.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * EmployeeHandler
 *
 * @author shuai
 * @date 2019/1/17
 */
@Controller
public class UserHandler {
	public UserHandler() {
		System.out.println("UserHandler");
	}

	private static User user;
	private static Address address;

	static {
		address = new Address("LiaoNing", "DaLian");
		user = new User(1, "A", "123456", 18, "a@aa.com");
		user.setAddress(address);
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("user", user);
		return "user/user";
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String add(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.getErrorCount() > 0) {
			System.out.println("出错了");
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
			}
		}
		return "user/user";
	}
}
