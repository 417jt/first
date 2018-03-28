package studyhttpclient;

import java.net.URL;
import java.util.LinkedList;

public class URLQueue {
	public LinkedList<String> urlQueue  = new LinkedList<String>();
	public final int MAX_URLS = 1000;
	public synchronized void addURL(String url) {
		urlQueue.add(url);
	}
	
	public synchronized String getURL() {
		return urlQueue.removeFirst();
	}
	public synchronized int getSize() {
		return urlQueue.size();
	}
	
}
