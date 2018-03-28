package first.fileio;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import javax.sound.sampled.Line;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FileIOTest {
    public static String line_separator = System.getProperty("line.separator");
    public static void main(String[] args) {
       
        
        
        LinkedList<String> queue = InitialURLS.urlQueue;
        execute(queue);
    }
    
    public static void execute(LinkedList<String> queue) {
        
        while (queue.size() > 0 && queue.size() < 1000) {
            String url = InitialURLS.getURL();
            System.out.println(url);
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.getElementsByTag("a");
                for(Element element : elements) {
                    String newUrl = element.attr("href");
                    if (!newUrl.startsWith("http")) {
                        continue;
                    }
                    write(newUrl);
                    InitialURLS.addURL(newUrl);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
    }
    
    public static void read(String name) {
        File file = new File(name);
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = in.readLine()) !=  null && !line.equals("") ) {
                System.out.println(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    public static void write(String url) {
        
       /* try {
            String file = "src/test/fileio/urls.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
              bw.append((CharSequence)url);
              bw.newLine();
              bw.close();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }*/
        
        try {
            String file = "src/test/fileio/urls.txt";
            RandomAccessFile ra = new RandomAccessFile(file, "rw");
             ra.seek(ra.length());;
             
            ra.writeBytes(url);
            ra.writeBytes(line_separator);
              ra.close();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
