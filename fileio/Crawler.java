package first.fileio;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import org.jsoup.Connection;
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
            if (count > 3) {
                break;
            }
            if (url != null && !"".equals(url)) {
                execute(url);
                try {
                    Thread.sleep((long)Math.random()*10 +10);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                
            }
        }
    }

public  void execute(String url) {
            try {
                Connection conn = Jsoup.connect(url).userAgent(UserAgents.getRandomAgent());
                Connection.Response resp = conn.execute();
                String html = resp.body();
                //System.out.println(html);
                
                Document doc = resp.parse();
                System.out.println(doc.text());
                Elements elements = doc.getElementsByTag("a");
                for(Element element : elements) {
                    String newUrl = element.attr("href");
                    if (!newUrl.startsWith("http")) {
                        continue;
                    }
                   // System.out.println(newUrl);
                    write(newUrl);
                    urlQueue.addURL(newUrl);
                    BloomFilter.add(newUrl);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }

    public  void write(String url) {
       /* try {
            String file = "src/main/resources/urls.txt";
            RandomAccessFile ra = new RandomAccessFile(file, "rw");
            ra.seek(ra.length());
            ra.writeBytes(url);
            ra.writeBytes(line_separator);
            ra.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }*/
    	try {
            String file = "src/main/resources/urls.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
              bw.append((CharSequence)url);
              bw.newLine();
              bw.close();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
