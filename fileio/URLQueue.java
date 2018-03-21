package test.fileio;

import java.util.LinkedList;

public class URLQueue {
    
    
    public  LinkedList<String> urlQueue = new LinkedList<>();
    public  final int max_deep = 10;
    
     public URLQueue (String url) {
         urlQueue.add(url);
        
    }
    public synchronized  void addURL(String url) {
        urlQueue.add(url);
    }
    
    public synchronized  String getURL() {
        return urlQueue.removeFirst();
    }
    
    public synchronized  boolean isEmpty() {
        return urlQueue.isEmpty();
    }
    
}
