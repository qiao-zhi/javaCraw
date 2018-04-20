package cn.qlq.craw.JsoupBaike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 解析器
 * @author liqiang
 *
 */
public class HtmlParser {
	/**
	 * 提取网站信息
	 * 
	 * @param newUrl
	 * @param htmlContent
	 * @return map中应该包含该网页上提取到的url地址(set集合)和关键字
	 */
	public Map<String, Object> parseHtml(String newUrl, String htmlContent) {
		// 0.将返回的htmlContent转换成DOM树
		// Document document = Jsoup.parse(htmlContent);
		// 1.获取到所有的a标签，且a标签的href属性包含/item
		List<String> urls = this.getUrls(newUrl, htmlContent);
		// 2.获取指定的标题和介绍
		Map<String, Object> titleAndSummary = this.getTitleAndSummary(newUrl, htmlContent);
		// 创建一个map,将提取到的urls和标题和简介装到map中返回去
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("urls", urls);
		result.put("titleAndSummary", titleAndSummary);
		return result;
	}

	/**
	 * 获取标题和简介
	 * 
	 * @param newUrl
	 *            传下来的访问的url
	 * @param htmlContent
	 *            传下来的获取到的html内容
	 * @return
	 */
	private Map<String, Object> getTitleAndSummary(String newUrl, String htmlContent) {
		Document document = Jsoup.parse(htmlContent);// 转换成DOM文档
		// 1.获取标题
		// first查找下面的第一个h1元素,get(index)可以获取指定位置的标签
		Element title_ele = document.select("dd.lemmaWgt-lemmaTitle-title").select("h1").first();
		String title_text = title_ele.text();
		// 2.获取简介
		Element summary_ele = document.select("div.lemma-summary").first();
		String summary_text = summary_ele.text();

		//将3.数据加入map返回
		Map<String, Object> titleAndSummary = new HashMap<String, Object>();
		titleAndSummary.put("title", title_text);
		titleAndSummary.put("summary", summary_text);
		titleAndSummary.put("url", newUrl);
		return titleAndSummary;
	}

	/**
	 * 获取到所有的a标签，且a标签的href属性包含/item
	 * 
	 * @param newUrl
	 * @param htmlContent
	 * @return
	 */
	private List<String> getUrls(String newUrl, String htmlContent) {
		Document document = Jsoup.parse(htmlContent);// 转换成DOM文档
		Elements elements = document.select("a[href^='/item']");// 查找以/item开头的元素的标签
		List<String> urls = new ArrayList<String>();
		for (Element ele : elements) {
			String url = newUrl.substring(0, newUrl.indexOf("/item")) + ele.attr("href");
			urls.add(url);
		}
		return urls;
	}

}
