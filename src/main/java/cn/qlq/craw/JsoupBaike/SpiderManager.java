package cn.qlq.craw.JsoupBaike;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 爬虫的入口
 * @author liqiang
 *
 */
public class SpiderManager {
	private UrlManager urlManager;
	private HtmlLoader htmlLoader;
	private HtmlOutputer htmlOutputer;
	private HtmlParser htmlParser;
	
	public SpiderManager() {
		super();
		this.urlManager =new  UrlManager();
		this.htmlLoader =new  HtmlLoader();
		this.htmlOutputer =new HtmlOutputer();
		this.htmlParser =new  HtmlParser();
	}
	
	
	/**
	 * 爬虫的主要逻辑
	 * @param url	需要爬的网站地址
	 */
	public void craw(String url){
		if(url == null || "".equals(url)){
			return;
		}
		int count = 0;//记录爬取了几个页面
		urlManager.addNewUrl(url);
		while (urlManager.hasNewUrl()){
			try {
				String newUrl = urlManager.getNewUrl();//获取需要爬取的网站url
				String htmlContent = htmlLoader.loadUrl(newUrl);//爬取网站内容
				Map<String,Object> datas = htmlParser.parseHtml(newUrl,htmlContent);//提取到爬到的网页中需要的信息
				urlManager.addNewUrls((List<String>) datas.get("urls"));//将提取到的信息保存到urlManager
				htmlOutputer.collectData(datas);//将提取到的数据收集起来
				if(count == 10){
					break;
				}
				count++;
			} catch (Exception e) {
				System.out.println("发生异常"+e.getMessage());
			}
		}
		try {
			htmlOutputer.outputDatas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UrlManager getUrlManager() {
		return urlManager;
	}
	public void setUrlManager(UrlManager urlManager) {
		this.urlManager = urlManager;
	}
	public HtmlLoader getHtmlLoader() {
		return htmlLoader;
	}
	public void setHtmlLoader(HtmlLoader htmlLoader) {
		this.htmlLoader = htmlLoader;
	}

	public HtmlOutputer getHtmlOutputer() {
		return htmlOutputer;
	}
	public void setHtmlOutputer(HtmlOutputer htmlOutputer) {
		this.htmlOutputer = htmlOutputer;
	}


	public HtmlParser getHtmlParser() {
		return htmlParser;
	}
	public void setHtmlParser(HtmlParser htmlParser) {
		this.htmlParser = htmlParser;
	}
}
