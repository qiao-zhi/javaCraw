package cn.qlq.craw.JsoupBaike;

import java.io.IOException;

import org.jsoup.Jsoup;
/**
 * 读取url的内容
 * @author liqiang
 *
 */
public class HtmlLoader {

	public String loadUrl(String newUrl) {
		try {
			return Jsoup.connect(newUrl).post().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
