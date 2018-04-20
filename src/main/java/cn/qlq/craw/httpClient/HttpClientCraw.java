package cn.qlq.craw.httpClient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientCraw {

	public static void main(String[] a) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod("http://webcache.googleusercontent.com/search?q=cache:http://jwc2017.tyust.edu.cn/");
		// 防止中文乱码
		postMethod.getParams().setContentCharset("utf-8");
		// 3.设置请求参数
		postMethod.setParameter("mobileCode", "13834786998");
		postMethod.setParameter("userID", "");
		// 4.执行请求 ,结果码
		int code = client.executeMethod(postMethod);
		// 5. 获取结果
		String result = postMethod.getResponseBodyAsString();
		System.out.println("Post请求的结果：" + result);
	}
}
