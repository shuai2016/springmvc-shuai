package xin.yangshuai.springmvc.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * HelloView
 *
 * @author shuai
 * @date 2019/1/17
 */
@Component
public class HelloView implements View {
	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.getWriter().println("hello view, time : " + new Date());
	}
}
