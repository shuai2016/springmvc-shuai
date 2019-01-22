package xin.yangshuai.springmvc.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * MyExceptionHandler
 *
 * @author shuai
 * @date 2019/1/22
 */
@ControllerAdvice
public class MyExceptionHandler {

	public static final String ERROR = "error";

	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleArithmeticException(Exception e) {
		System.out.println("handleArithmeticException");
		System.out.println("出异常了 : " + e);
		ModelAndView modelAndView = new ModelAndView(ERROR);
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
}
