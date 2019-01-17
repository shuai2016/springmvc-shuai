package xin.yangshuai.springmvc.handlers;

import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xin.yangshuai.springmvc.entities.Address;
import xin.yangshuai.springmvc.entities.User;

import java.util.Map;

/**
 * EmployeeHandler
 *
 * @author shuai
 * @date 2019/1/17
 */
@Controller
public class UserHandler {

	private static User user;
	private static Address address;
	static {
		address = new Address("LiaoNing","DaLian");
		user = new User(1, "A", "123456", 18, "a@aa.com");
		user.setAddress(address);
	}

	@RequestMapping(value = "user",method = RequestMethod.GET)
	public String add(Map<String,Object> map){
		map.put("user",user);
		return "user/user";
	}
}
