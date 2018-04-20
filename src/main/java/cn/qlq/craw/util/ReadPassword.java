package cn.qlq.craw.util;

import java.io.Console;

/**
 * 控制台读取密码的练习
 * 
 * @author liqiang
 *
 */
public class ReadPassword {
	public static void main(String[] args) {
		System.out.print("请输入密码:");
		Console con = System.console();
		String pswd = new String(con.readPassword());// 因为读取的是字符数组,所以需要用new
		System.out.println("你刚刚输入的密码是" + pswd);
	}
}
