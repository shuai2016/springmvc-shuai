package xin.yangshuai.springmvc.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * DataUtils
 *
 * @author shuai
 * @date 2019/1/21
 */
public class DataUtils {
	/**
	 * 判断浏览器种类的方法
	 *
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (UserAgent != null) {
			if (UserAgent.indexOf("msie") >= 0) {
				return "IE";
			}
			if (UserAgent.indexOf("firefox") >= 0) {
				return "FF";
			}

			if (UserAgent.indexOf("safari") >= 0) {
				return "SF";
			}
		}
		return null;
	}
}
