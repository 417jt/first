package first.lucene;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneTest {
	private IndexWriter writer;
	public LuceneTest(String indexDir) throws Exception{
		Directory dir = FSDirectory.open(Paths.get(indexDir));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		writer = new IndexWriter(dir,iwc);
	}
	
	public void close() throws Exception{
		writer.close();
	}
	
	public int index (String dataDir) throws Exception{
		File[] files = new File(dataDir).listFiles();
		for(File f : files) {
			indexFile(f);
		}
		return writer.numDocs();
	}
	
	
	private void indexFile (File f) throws Exception {
		System.out.println("索引文件；" + f.getCanonicalPath());
		Document doc = getDocument(f);
		writer.addDocument(doc);
		
	}
	
	private Document getDocument(File f) throws Exception {
		Document doc = new Document();
		doc.add(new TextField("content", new FileReader(f)));
		doc.add(new TextField("fileName", f.getName(),Field.Store.YES));
		doc.add(new TextField("fullPath", f.getCanonicalPath(),Field.Store.YES));
		return doc;
	}
	
	public static void main(String[] args) {
		String indexDir = "D:\\lucene\\index";
		String dataDir = "D:\\lucene\\data";
		LuceneTest test = null;
		int numindexed = 0;
		long start = System.currentTimeMillis();
		try {
			test = new LuceneTest(indexDir);
			numindexed =test.index(dataDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				test.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println("索引了 " + numindexed + "个文件 花费了" + (end - start) + "毫秒");;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
