package cn.qlq.craw.Jsoup;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 模拟Jsoup跳过登陆进行爬虫(伪造成浏览器去请求数据)
 * 
 * @author liqiang
 *
 */
public class JsoupCookieCraw {
	private static Map<String, String> cookies = null;

	public static void main(String[] args) throws IOException {
		// 先模拟登录获取到cookie和sessionid并存到全局变量cookies中
		String url = "http://qiaoliqiang.cn/Exam/view/testPerson/outEmployeeAllot.jsp";
		// 直接获取DOM树，带着cookies去获取
		Document document = Jsoup.connect(url).post();
		System.out.println(document.toString());
	}

	/**
	 * 模拟登录获取cookie和sessionid
	 */
	public static void login() throws IOException {
		String urlLogin = "http://qiaoliqiang.cn/Exam/user_login.action";
		Connection connect = Jsoup.connect(urlLogin);
		// 伪造请求头
		connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
				"gzip, deflate");
		connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
		connect.header("Content-Length", "72").header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		connect.header("Host", "qiaoliqiang.cn").header("Referer", "http://qiaoliqiang.cn/Exam/");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
				.header("X-Requested-With", "XMLHttpRequest");

		// 携带登陆信息
		connect.data("username", "362501197407067215").data("password", "123456").data("user_type", "2")
				.data("isRememberme", "yes");

		// 请求url获取响应信息
		Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
		// 获取返回的cookie
		cookies = res.cookies();
		for (Entry<String, String> entry : cookies.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		System.out.println("---------华丽的分割线-----------");
		String body = res.body();// 获取响应体
		System.out.println(body);
	}

}
