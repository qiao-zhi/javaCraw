package cn.qlq.craw.JsoupBaike;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * url管理器
 * @author liqiang
 *
 */
@SuppressWarnings("all")
public class UrlManager {

	/**
	 * 存放未被访问的url的list
	 */
	private List<String> new_urls;
	/**
	 * 存放已经访问过的url的list
	 */
	private List<String> old_urls;
	public UrlManager() {
		this.new_urls = new LinkedList<String>();
		this.old_urls = new LinkedList<String>();
	}
	
	/**
	 * 添加一个url到list中
	 * @param url
	 */
	public void addNewUrl(String url) {
		if(url == null || "".equals(url)){
			return;
		}
		if(!new_urls.contains(url) && !old_urls.contains(url) ){
			new_urls.add(url);
		}
	}

	/**
	 * 判断是否有新的url
	 * @return
	 */
	public boolean hasNewUrl() {
		return new_urls.size()>0;
	}

	/**
	 * 弹出一个新的url
	 * @return
	 */
	public String getNewUrl() {
		if(new_urls.size() == 0){
			return null;
		}
		String newUrl = new_urls.get(0);//从未访问的集合中获取一个数据
		new_urls.remove(0);//移除第一个元素
		old_urls.add(newUrl);//将移除的数据添加到旧的已经访问过的集合中
		return newUrl;
	}

	/**
	 * 批量添加url
	 * @param urls	需要添加的url集合
	 */
	public void addNewUrls(List<String> urls) {
		if(urls == null || urls.size()==0){
			return;
		}
		for(String url:urls){
			this.addNewUrl(url);
		}
	}
	
	
	public List<String> getNew_urls() {
		return new_urls;
	}

	public void setNew_urls(List<String> new_urls) {
		this.new_urls = new_urls;
	}

	public List<String> getOld_urls() {
		return old_urls;
	}

	public void setOld_urls(List<String> old_urls) {
		this.old_urls = old_urls;
	}
	
}
