package cn.qlq.craw.JsoupCrawJWXT;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * 主的程序调度器
 * 
 * @author liqiang
 *
 */
public class MainClass {

	public static void main(String[] args) {
		
		//输入学号和密码		
		System.out.print("请输入你要查询学号:");
		Scanner sc = new Scanner(System.in);
		String xuehao = sc.next();
	    System.out.print("请输入密码:");
	    //String password = sc.next();
	    Console con = System.console();
	    String password= new String(con.readPassword());// 因为读取的是字符数组,所以需要用new
	    
		// 1.访问主页，获取验证码与viewstate
		try {
			DownloadLoginfo downloadLoginfo = new DownloadLoginfo();
			LoginClass loginClass = new LoginClass();
			GradeOutput gradeOutput = new GradeOutput();
			downloadLoginfo.getLogInfo();
			// 2.登录
			loginClass.login(downloadLoginfo,xuehao,password);
			for (Entry<String, String> entry : loginClass.getCookies().entrySet()) {
				System.out.println("key:" + entry.getKey() + ";value" + entry.getValue());
			}
			CrawGrade crawGrade = new CrawGrade();
			// 爬取成绩的上一个页面
			crawGrade.crawGradeLastPage(downloadLoginfo.getCookies(), downloadLoginfo.getViewState(),xuehao);
			List<String> condition = geneQueryCondition();
			for (String xuenian : condition) {
				String html_content = crawGrade.crawGrade(xuenian, "2", downloadLoginfo.getCookies(),
						// 爬取成绩页面
						downloadLoginfo.getViewState(),xuehao);
				gradeOutput.collectGrade(html_content);

			}
			gradeOutput.outputDatas2Html();
		} catch (IOException e) {
			System.out.println("无法连接学校服务器");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造需要查询的年份和学期
	 * 
	 * @return
	 */
	public static List<String> geneQueryCondition() {
		List<String> condition = new ArrayList<String>();
		condition.add("2014-2015");
		condition.add("2015-2016");
		condition.add("2016-2017");
		condition.add("2017-2018");
		return condition;
	}

}
