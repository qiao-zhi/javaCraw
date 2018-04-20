package cn.qlq.craw.Jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class IOutilsDownloadFile {
	public static void main(String[] args) throws IOException {
		String url = "http://qiaoliqiang.cn/fileDown/zfb.bmp";
		URL url1 = new URL(url);
		URLConnection conn = url1.openConnection();
		InputStream inputStream = conn.getInputStream();
		String path = "C:\\Users\\liqiang\\Desktop\\test.bmp";
		OutputStream outputStream = new FileOutputStream(path);
		// 利用IOutiks拷贝文件，简单快捷
		IOUtils.copy(inputStream, outputStream);
	}
}
