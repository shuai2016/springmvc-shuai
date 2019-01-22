package xin.yangshuai.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserNameNotMatchPasswordException
 *
 * @author shuai
 * @date 2019/1/22
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名和密码不匹配！")
public class UserNameNotMatchPasswordException extends RuntimeException {
	private static final long serialVersionUID = -7645726278844952650L;
}
