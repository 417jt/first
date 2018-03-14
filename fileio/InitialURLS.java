package test.fileio;

import java.util.LinkedList;

public class InitialURLS {
    public static LinkedList<String> urlQueue = new LinkedList<>();
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
}
