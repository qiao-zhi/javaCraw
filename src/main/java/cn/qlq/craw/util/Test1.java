package cn.qlq.craw.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

/**
 * FileWriter 和 FileReader写文件和读文件的练习
 * 
 * @author liqiang
 *
 */
public class Test1 {
	@Test
	public void test1() throws IOException {
		File file = new File("./test.txt");
		if (!file.exists()) {// 如果文件不存在就创建文件
			file.createNewFile();
		}
		Writer writer = new FileWriter(file);// 此构造方法也接收一个URI参数
		writer.write("this is test");
		writer.close();
	}

	@Test
	public void test2() throws IOException {
		File file = new File("./test.txt");
		Reader reader = new FileReader("./test.txt");// 此构造方法也接收一个URI参数
		int read = 0;
		while ((read = reader.read()) != -1) {
			System.out.print((char) read);
		}
		reader.close();
	}
}
