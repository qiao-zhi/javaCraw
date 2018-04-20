package cn.qlq.craw.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
public static void main(String[] args) throws IOException {
    JsoupTest jsoupTest = new JsoupTest();
    String url = "http://tieba.baidu.com/p/4549504175";
    // 1.jsoup 的简单应用
    jsoupTest.getHtmlElements(url);
}

private static int count = 0;

// 爬取网络的图片到本地
public void saveToFile(String destUrl) {

    FileOutputStream fos = null;
    BufferedInputStream bis = null;
    HttpURLConnection httpUrl = null;
    URL url = null;
    int BUFFER_SIZE = 1024;
    byte[] buf = new byte[BUFFER_SIZE];
    int size = 0;
    try {
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.connect();
        bis = new BufferedInputStream(httpUrl.getInputStream());
        String imgName = destUrl.substring(7, destUrl.lastIndexOf("."));
        System.out.println(imgName);
        File dir = new File("G:/爬虫图片");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File("G:/爬虫图片/craw" + count + ".jpg");
        System.out.println(file.getAbsolutePath());

        fos = new FileOutputStream(file);
        while ((size = bis.read(buf)) != -1) {
            fos.write(buf, 0, size);
        }
        fos.flush();
    } catch (IOException e) {
        System.out.println("IOException");
    } catch (ClassCastException e) {
        System.out.println("ClassCastException");
    } finally {
        count++;
        try {
            fos.close();
            bis.close();
            httpUrl.disconnect();
        } catch (IOException e) {
        } catch (NullPointerException e) {
        }
    }
}

    // 解析url的元素
    private void getHtmlElements(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            // 获取后缀名为jpg的img元素
            Elements pngs = doc.select("img");
            for (Element element : pngs) {
                saveToFile(element.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}