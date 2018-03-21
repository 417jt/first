package test.fileio;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.RandomAccessFile;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler extends Thread {
    URLQueue urlQueue = null;
    private int  count= 0;
    public static String line_separator = System.getProperty("line.separator");

    public Crawler(String url) {
        urlQueue = new URLQueue(url);
        BloomFilter.add(url);
    }

    public void run() {
        
        while (!urlQueue.isEmpty()) {

            String url = urlQueue.getURL();
            count++;
            if (count > 10) {
                break;
            }
            if (url != null && !"".equals(url)) {
                execute(url);
            }
        }
    }

public  void execute(String url) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.getElementsByTag("a");
                for(Element element : elements) {
                    String newUrl = element.attr("href");
                    if (!newUrl.startsWith("http")) {
                        continue;
                    }
                    write(newUrl);
                    urlQueue.addURL(newUrl);
                    BloomFilter.add(newUrl);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }

    public  void write(String url) {
        try {
            String file = "src/test/fileio/urls.txt";
            RandomAccessFile ra = new RandomAccessFile(file, "rw");
            ra.seek(ra.length());
            ra.writeBytes(url);
            ra.writeBytes(line_separator);
            ra.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
