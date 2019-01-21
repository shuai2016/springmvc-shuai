package xin.yangshuai.springmvc.download;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xin.yangshuai.springmvc.utils.DataUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * DownLoad
 *
 * @author shuai
 * @date 2019/1/21
 */
@Controller
public class DownLoad {

	@RequestMapping("downloadMusic")
	public ResponseEntity<byte[]> downloadMusic(String fileName, String musicUrl, HttpServletRequest request) throws IOException {

		URL url = new URL(musicUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//得到输入流
		InputStream inputStream = conn.getInputStream();
		//获取自己数组
		byte[] body = readInputStream(inputStream);

		fileName = fileName + ".mp3";
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

	/**
	 * 从输入流中获取字节数组
	 *
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
}
