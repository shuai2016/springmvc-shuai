package xin.yangshuai.springmvc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHtmlTitle {

	//源码中标题正则表达式
	private static Pattern pa = Pattern.compile("<title>.*?</title>");

	/**
	 * 根据网址返回网页的源码
	 *
	 * @param htmlUrl
	 * @return
	 */
	public static String getHtmlSource(String htmlUrl) {
		URL url;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(htmlUrl);
			//读取网页全部内容
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String temp;
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 从html源码(字符串)中去掉标题
	 *
	 * @param htmlSource
	 * @return
	 */
	public static String getTitle(String htmlSource) {
		List<String> list = new ArrayList<String>();
		String title = "";

		//Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以

		Matcher ma = pa.matcher(htmlSource);
		//寻找符合el的字串
		while (ma.find()) {
			//将符合el的字串加入到list中
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return outTag(title);
	}

	/**
	 * 去掉html源码中的标签
	 *
	 * @param s
	 * @return
	 */
	public static String outTag(String s) {
		return s.replaceAll("<.*?>", "");
	}

	public static void main(String[] args) {
		String htmlUrl = "https://music.163.com/#/song?id=27771269";
		System.out.println("/n------------开始读取网页(" + htmlUrl + ")-----------");
		String htmlSource = "";
		//获取htmlUrl网址网页的源码
		htmlSource = getHtmlSource(htmlUrl);
		System.out.println("------------读取网页(" + htmlUrl + ")结束-----------/n");
		System.out.println("------------分析(" + htmlUrl + ")结果如下-----------/n");
		String title = getTitle(htmlSource);
		System.out.println("网站标题： " + title);
	}
}