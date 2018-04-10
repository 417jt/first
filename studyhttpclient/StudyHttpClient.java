package first.studyhttpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class StudyHttpClient {
	public static void main(String[] args) {
		/*
		 * HttpUriRequest get = new HttpGet("https://www.baidu.com"); HttpUriRequest
		 * post = new HttpPost("https:www.zhihu.com"); doRequest(get, "UTF-8");
		 */
		String file = "src/main/resources/root_urls.txt";
		URLQueue queue = new URLQueue();
		readSource(file,queue);
		begin(queue);
	}

	public static void readSource(String name,URLQueue queue) {
		File file = new File(name);

		String line = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((line = in.readLine()) != null && !line.equals("")) {
				queue.addURL(line);;
				System.out.println(line);
			}
			in.close();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}

	}
	public static void  begin(URLQueue queue) {
		int count = 0;
		while (queue.getSize() > 0) {
			String url = queue.getURL();
			processURL(url);
			count++;
		}
		System.out.println("爬虫完成，共爬取 " + count +" 个网，"+System.getProperty("file.encoding")+Charset.defaultCharset());
		
	}
	public static void processURL(String url) {
		HttpGet req = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig reConfig = RequestConfig.custom().setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000).setSocketTimeout(10000).build();
		req.setConfig(reConfig);
		
		try {
			CloseableHttpResponse resp = httpClient.execute(req);
			HttpEntity entity = resp.getEntity();
			if (entity != null && resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				storeDocument(EntityUtils.toByteArray(entity),url);
			}
		} catch (Exception e) {
			System.err.println("爬取 "+url+ "时出错");
			e.printStackTrace();
			// TODO: handle exception
			
		}
	}
	
	public static void storeDocument(byte[] arr,String url) {
		File file = new File("G:\\documents\\"+url.substring(url.indexOf(":") + 3, url.lastIndexOf("/")).replace('.','_')+".html");
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
			out.write(arr);
			out.close();
		} catch (Exception e) {
			System.err.println("爬取 "+url+ "时出错");
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

	public static void doRequest(HttpUriRequest req, String charset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse resp = httpClient.execute(req);
			System.out.println(resp.getStatusLine());
			HttpEntity entity = resp.getEntity();
			if (entity != null && resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println(new String(EntityUtils.toByteArray(entity), charset));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
