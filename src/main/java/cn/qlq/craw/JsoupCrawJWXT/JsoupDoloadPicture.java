package cn.qlq.craw.JsoupCrawJWXT;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Jsoup带着cookie下载验证码到本地(必须带着cookie下载验证码，否则下载的验证码无效)
 * 
 * @author liqiang
 *
 */
public class JsoupDoloadPicture {

	/**
	 * 带着cookie下载验证码图片
	 * 
	 * @param url
	 * @param cookies
	 * @throws IOException
	 */
	public static void downloadImg(String url, Map<String, String> cookies) throws IOException {
		// TODO Auto-generated method stub
		Connection connect = Jsoup.connect(url);
		connect.cookies(cookies);// 携带cookies爬取图片
		connect.timeout(5 * 10000);
		Connection.Response response = connect.ignoreContentType(true).execute();
		byte[] img = response.bodyAsBytes();
		System.out.println(img.length);
		// 读取文件存储位置
		String directory = ResourcesUtil.getValue("path", "file");
		savaImage(img, directory, "yzm.png");
	}

	/**
	 * 保存图片到本地
	 * @param img
	 * @param filePath
	 * @param fileName
	 */
	public static void savaImage(byte[] img, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		File dir = new File(filePath);
		try {
			// 判断文件目录是否存在
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdir();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(img);
			System.out.println("验证码已经下载到:"+filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}