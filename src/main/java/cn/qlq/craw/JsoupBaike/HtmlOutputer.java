package cn.qlq.craw.JsoupBaike;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 输出器
 * 
 * @author liqiang
 *
 */
public class HtmlOutputer {

	private List<Map<String, Object>> collected_datas;

	public HtmlOutputer() {
		this.collected_datas = new ArrayList<Map<String, Object>>();
	}

	/**
	 * 收集数据
	 * 
	 * @param datas
	 */
	public void collectData(Map<String, Object> datas) {
		collected_datas.add(datas);// 将数据 添加到集合中
	}

	/**
	 * 最后处理所有的数据，写出到html或者保存数据库
	 * 
	 * @throws IOException
	 */
	public void outputDatas() throws IOException {
		if (collected_datas != null && collected_datas.size() > 0) {
			File file = new File("C:\\Users\\liqiang\\Desktop\\实习\\python\\JavaCraw\\out.html");
			// 如果文件不存在就创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			// 构造FileWriter用于向文件中输出信息(此构造方法可以接收file参数，也可以接收fileName参数)
			FileWriter fileWriter = new FileWriter(file);
			// 开始写入数据
			fileWriter.write("<html>");
			fileWriter.write("<head>");
			fileWriter.write("<title>爬取结果</title>");
			fileWriter
					.write("<style>table{width:100%;table-layout: fixed;word-break: break-all; word-wrap: break-word;}"
							+ "table td{border:1px solid black;width:300px}</style>");
			fileWriter.write("</head>");
			fileWriter.write("<body>");
			fileWriter.write("<table cellpadding='0' cellspacing='0'>");
			for (Map<String, Object> datas : collected_datas) {
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) datas.get("titleAndSummary");
				String url = (String) data.get("url");
				String title = (String) data.get("title");
				String summary = (String) data.get("summary");
				fileWriter.write("<tr>");
				fileWriter.write("<td><a href=" + url + ">" + url + "</a></td>");
				fileWriter.write("<td>" + title + "</td>");
				fileWriter.write("<td>" + summary + "</td>");
				fileWriter.write("</tr>");

			}
			fileWriter.write("</table>");
			fileWriter.write("</body>");
			fileWriter.write("</html>");
			// 关闭文件流
			fileWriter.close();
		}
	}

}
