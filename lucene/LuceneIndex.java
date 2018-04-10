package first.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneIndex {
	private IndexWriter writer;
	private static class TextFilesFilter implements FileFilter{
		public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".txt");
		}
	}
	
	public LuceneIndex (String indexDir) throws IOException{
		Directory dir = FSDirectory.open(Paths.get(indexDir));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);
		writer = new IndexWriter(dir, config);
		
		
	}

}
