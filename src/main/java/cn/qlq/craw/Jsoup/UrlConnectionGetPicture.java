package cn.qlq.craw.Jsoup;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * url获取图片并且保存到本地
 * 
 * @author liqiang
 *
 */
public class UrlConnectionGetPicture {
	
	public static void main(String[] args) throws Exception {
		String url  = "http://qiaoliqiang.cn/fileDown/zfb.bmp";
		URL url1 = new URL(url);
		URLConnection conn = url1.openConnection();
		InputStream inputStream = conn.getInputStream();
		String path = "C:\\Users\\liqiang\\Desktop\\实习\\python\\javaCrawPicture\\test.bmp";
		OutputStream out = new FileOutputStream(path);
		byte[] buff = new byte[1024];
		int i = -1;
		while(( i = inputStream.read(buff))!= -1){
			out.write(buff, 0 , i);
		}
		inputStream.close();
		out.close();
	}
	
}
