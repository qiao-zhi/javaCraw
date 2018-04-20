package cn.qlq.craw.JsoupCrawJWXT;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * 登录类(访问登录页面获取登录的cookie)
 * 
 * @author liqiang
 *
 */
public class LoginClass {
	/**
	 * 记录返回的cookie
	 */
	private Map<String, String> cookies = null;

	/**
	 * 模拟登录获取cookie和sessionid
	 * 
	 */
	public void login(DownloadLoginfo downloadLoginfo, String xuehao, String mima) throws Exception {
		String urlLogin = "http://newjwc.tyust.edu.cn/default2.aspx";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// 伪造请求头
		connect.header("Content-Length", "213").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer",
				"http://newjwc.tyust.edu.cn/xscjcx.aspx?xh=" + xuehao + "&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		// 输入验证码
		System.out.println("-----------请输入验证码---------");
		Scanner sc = new Scanner(System.in);
		String yzm = sc.next();
		sc.close();
		// 携带登陆信息
		connect.data("txtUserName", xuehao).data("__VIEWSTATE", downloadLoginfo.getViewState()).data("TextBox2", mima)
				.data("Textbox1", "").data("RadioButtonList1", "").data("Button1", "").data("lbLanguage", "")
				.data("hidPdrs", "").data("hidsc", "").data("txtSecretCode", yzm);
		connect.cookies(downloadLoginfo.getCookies());
		// 请求url获取响应信息
		Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
		// 获取返回的cookie
		this.cookies = res.cookies();
		for (Entry<String, String> entry : cookies.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		System.out.println("---------获取的登录之后的页面-----------");
		String body = res.body();// 获取响应体
		System.out.println(body);
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

}
