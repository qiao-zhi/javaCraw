package cn.qlq.craw.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

/**
 * 一个爬取https和http的工具类
 * 
 * @author liqiang
 *
 */
public class HttpCommonUtil {

	public static void trustEveryone() {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getHttpHeaders(URL url, int timeout) {
		try {
			trustEveryone();
			Connection conn = HttpConnection.connect(url);
			conn.timeout(timeout);
			conn.header("Accept-Encoding", "gzip,deflate,sdch");
			conn.header("Connection", "close");
			conn.get();
			// String result=conn.response().body();
			Map<String, String> result = conn.response().headers();
			result.put("title", conn.response().parse().title());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getHttpBody(URL url, int timeout) {
		try {
			trustEveryone();
			Connection conn = HttpConnection.connect(url);
			conn.timeout(timeout);
			conn.header("Accept-Encoding", "gzip,deflate,sdch");
			conn.header("Connection", "close");
			conn.get();
			// String result=conn.response().body();
			// String result = conn.response().body();
			String result = conn.response().body();
			File file = new File("C:\\Users\\liqiang\\Desktop\\实习\\python\\javaCrawPicture\\tianmao.html");
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
			}
			file.createNewFile();
			Writer fileWriter = new FileWriter(file);
			fileWriter.write(result);
			fileWriter.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			URL url = new URL("http", "www.tmall.com", -1, "");
			System.out.println(getHttpBody(url, 100000));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}