package cn.qlq.craw.JsoupBaike;

import java.io.FileWriter;

public class MainClass {

	public static void main(String[] args) {
		String url = "https://baike.baidu.com/item/Python/407313";
		SpiderManager sm = new SpiderManager();
		sm.craw(url);
	}

}
