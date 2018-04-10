/*package first.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class IndexAndSearch {
	private static Document createDocument(String title, String content) {
		Document doc = new Document();
		doc.add(new Field("content", content, TextField.TYPE_NOT_STORED));
		doc.add(new Field("title", title,TextField.TYPE_STORED));
		doc.add(new Field("author", "faker",TextField.TYPE_STORED));
		return doc;
	}
	
	public static void testDemo() throws Exception{
		Analyzer analyzer = new StandardAnalyzer();
		Directory idx = new RAMDirectory();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(idx, iwc);
		writer.addDocument(createDocument("中央政治局研究2017年经济工作会议","中共中央政治局12月9日召开会议，分析研究2017年经济工作，审议通过《关于加强国家安全工作的意见》。中共中央总书记习近平主持召开会议"));
		writer.commit();
		writer.close();
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(idx));
		System.out.println("命中个数：" + searcher.search(new queryp, collectorManager))
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
*/