package xin.yangshuai.springmvc.file;

import java.io.*;

/**
 * Copy
 *
 * @author shuai
 * @date 2019/2/26
 */
public class Copy {
	public static void main(String[] args) throws IOException {
		String path0 = "E:\\myFile\\test2\\123.jpg";
		String path1 = "E:\\myFile\\test2\\456.jpg";
		InputStream in = new FileInputStream(path0);
		OutputStream out = new FileOutputStream(path1);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}
}
