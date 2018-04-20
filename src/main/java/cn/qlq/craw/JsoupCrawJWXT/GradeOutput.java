package cn.qlq.craw.JsoupCrawJWXT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 收集成绩与输出成绩
 * 
 * @author liqiang
 *
 */
@SuppressWarnings("all")
public class GradeOutput {
	/**
	 * 保存成绩的集合
	 */
	private List<Map<String, Object>> datas;

	public GradeOutput() {
		this.datas = new ArrayList<Map<String, Object>>();
	}

	/**
	 * 收集成绩
	 * 
	 * @param html
	 * @return
	 */
	public String collectGrade(String html) {
		// 解析html
		Document document = Jsoup.parse(html);
		// 获取成绩表格
		Element table = document.select("#Datagrid1").first();
		// 选择除表格表头之外的元素
		Elements trs = table.select("tr:gt(0)");
		for (Element ele : trs) {
			Map result = new LinkedHashMap();
			Elements ele0 = ele.select("td:eq(0)");// 找到学年
			result.put("xuenian", ele0.text());
			Elements ele1 = ele.select("td:eq(1)");// 找到学期
			result.put("xueqi", ele1.text());
			Elements ele3 = ele.select("td:eq(3)");// 找到课程名称
			result.put("kecheng", ele3.text());
			Elements ele8 = ele.select("td:eq(8)");// 找到成绩
			result.put("chengji", ele8.text());
			this.datas.add(result);
		}
		return null;
	}

	/**
	 * 输出成绩到控制台
	 */
	public void outPutGrade() {
		if (this.datas == null || this.datas.size() == 0) {
			return;
		}
		System.out.println("-------下面是提取到的成绩--------");
		for (Map result : datas) {

			System.out.println(result.get("xuenian") + "\t" + result.get("xueqi") + "\t" + result.get("kecheng") + "\t"
					+ result.get("chengji") + "\t");
		}

	}

	/**
	 * 最后处理所有的数据，写出到html或者保存数据库
	 * 
	 * @throws IOException
	 */
	public void outputDatas2Html() throws IOException {
		if (datas != null && datas.size() > 0) {
			// 读取文件存储位置
			String directory = ResourcesUtil.getValue("path", "file");
			
			File file = new File(directory+"\\gradeOut.html");
			// 如果文件不存在就创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			// 构造FileWriter用于向文件中输出信息(此构造方法可以接收file参数，也可以接收fileName参数)
			FileWriter fileWriter = new FileWriter(file);
			// 开始写入数据
			fileWriter.write("<html>");
			fileWriter.write("<head>");
			fileWriter.write("<title>xxx成绩单</title>");
			fileWriter
					.write("<style>table{width:100%;table-layout: fixed;word-break: break-all; word-wrap: break-word;}"
							+ "table td{border:1px solid black;width:300px}</style>");
			fileWriter.write("</head>");
			fileWriter.write("<body>");
			fileWriter.write("<table cellpadding='0' cellspacing='0' style='text-align:center;'>");
			fileWriter.write(
					"<tr style='background-color:#95caca;font-size:20px'><td>学年</td><td>学期</td><td>课程名字</td><td>成绩</td></tr>");

			for (Map<String, Object> data : datas) {
				String xuenian = (String) data.get("xuenian");
				String xueqi = (String) data.get("xueqi");
				String kecheng = (String) data.get("kecheng");
				String chengji = (String) data.get("chengji");
				fileWriter.write("<tr>");
				fileWriter.write("<td>" + xuenian + "</td>");
				fileWriter.write("<td>" + xueqi + "</td>");
				fileWriter.write("<td>" + kecheng + "</td>");
				fileWriter.write("<td>" + chengji + "</td>");
				fileWriter.write("</tr>");

			}
			fileWriter.write("</table>");
			fileWriter.write("</body>");
			fileWriter.write("</html>");
			// 关闭文件流
			fileWriter.close();
		}
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
