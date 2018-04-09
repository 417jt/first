package first.fileio;

import java.util.LinkedList;

public class InitialURLS {
    public static LinkedList<String> urlQueue = new LinkedList<String>();
    public static final int max_deep = 10;
    static {
        urlQueue.add("https://www.baidu.com/");
        urlQueue.add("https://www.zhihu.com/");
        urlQueue.add("http://www.qq.com/");
        urlQueue.add("http://www.sina.com.cn/");
        urlQueue.add("http://www.163.com/");
        urlQueue.add("http://www.ifeng.com/");
        urlQueue.add("http://www.sohu.com/");
        urlQueue.add("https://www.taobao.com/");
    }
    public synchronized static void addURL(String url) {
        urlQueue.add(url);
    }
    
    public synchronized static String getURL() {
        return urlQueue.removeFirst();
    }
    public static final String url1 = "https://www.baidu.com/";
    public static final String url2 = "https://www.zhihu.com/";
    public static final String url3 = "http://www.qq.com/";
    public static final String url4 = "http://www.sina.com.cn/";
    public static final String url5 = "http://www.163.com/";
    public static final String url6 = "http://www.ifeng.com/";
    public static final String url7 = "http://www.sohu.com/";
    public static final String url8 = "https://www.taobao.com/";
       
}
