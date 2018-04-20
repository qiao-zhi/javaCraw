package cn.qlq.craw.JsoupCrawJWXT;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 爬取成绩的类
 * 
 * @author liqiang
 *
 */
public class CrawGrade {
	
	private String viewState;
	/**
	 * 全局获取viewstate的函数
	 * @param html
	 * @return
	 */
	public  String getViewState(String html){
		Document document = Jsoup.parse(html);
		Element ele = document.select("input[name='__VIEWSTATE']").first();
		String value = ele.attr("value");
		this.viewState = value;
		// 获取到viewState
		return value;
	}

	/**
	 * 爬取获取成绩的上一个页面(也就是刚登陆之后的页面)
	 * @param cookies
	 * @param viewStata
	 * @param xuehao
	 * @return
	 * @throws IOException
	 */
	public String crawGradeLastPage(Map<String,String> cookies,String viewStata,String xuehao) throws IOException{
		String urlLogin = "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh="+xuehao+"&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// 伪造请求头
		connect.header("Content-Length", "74642").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh=201420020123&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		// 携带登陆信息
		connect.data("xh","201420020123")
			.data("xm", viewStata)
			.data("hidLanguage", "")
			.data("gnmkdm", "N121613");
		//设置cookie
		connect.cookies(cookies);
		
		Document document = connect.post();
		System.out.println("-----------爬到的成绩的上一个页面--------------");
		String html = document.toString();
		System.out.println(html);
		// 重新获取到viewState
		this.getViewState(html);
		return html;

		
	}
	
	/**
	 * 爬取成绩页面
	 */
	public String crawGrade(String xuenian,String xueqi,Map<String,String> cookies,String viewStata,String xuehao) throws IOException{
		String urlLogin = "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh="+xuehao+"&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// 伪造请求头
		connect.header("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.header("Accept-Encoding", "gzip, deflate");
		connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
		connect.header("Content-Length", "74642").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh=201420020123&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		
		// 携带登陆信息
		connect.data("__EVENTTARGET","")
		.data("__EVENTARGUMENT", "")
		.data("__VIEWSTATE", this.viewState)
		.data("hidLanguage","")
		.data("ddlXN", xuenian)
		.data("ddlXQ", xueqi)
		.data("btn_xn", "")
		.data("ddl_kcxz", "");
		
		connect.cookies(cookies);
		
		Document document = connect.post();
		System.out.println("-----------爬到的成绩的页面--------------");
		String html = document.toString();
		//更新viewstate
		this.getViewState(html);
		System.out.println(html);
		return html;
	}

	public void setViewState(String viewState) {
		this.viewState = viewState;
	}
	
	
	
}
