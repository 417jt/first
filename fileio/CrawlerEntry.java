package test.fileio;

public class CrawlerEntry {
    public static void main(String[] args) {
        Crawler crawler1 = new Crawler(InitialURLS.url1);
        Crawler crawler2 = new Crawler(InitialURLS.url2);
        Crawler crawler3 = new Crawler(InitialURLS.url3);
        Crawler crawler4 = new Crawler(InitialURLS.url4);
        Crawler crawler5 = new Crawler(InitialURLS.url5);
        Crawler crawler6 = new Crawler(InitialURLS.url6);
        Crawler crawler7 = new Crawler(InitialURLS.url7);
        Crawler crawler8 = new Crawler(InitialURLS.url8);
        crawler1.start();
        crawler2.start();
        crawler3.start();
        crawler4.start();
        crawler5.start();
        crawler6.start();
        crawler7.start();
        crawler8.start();
    }
   
}
