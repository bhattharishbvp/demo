package com.howtodoinjava.demo.lucene.document;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.facet.FacetsConfig;
import org.apache.lucene.facet.sortedset.SortedSetDocValuesFacetField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class LuceneWriteIndexExample 
{
	private static final String INDEX_DIR = "c:/temp/lucene6index";

	private static FacetsConfig config = new FacetsConfig();
	
	public static void main(String[] args) throws Exception 
	{
		config.setIndexFieldName("firstName", "facet_fn");
		config.setIndexFieldName("website", "facet_website");
		
		IndexWriter writer = createWriter();
    	
		List<Document> documents = new ArrayList<>();
		
		//Let's clean everything first
		writer.deleteAll();
				
		Document document1 = createDocument(1, "Lokesh", "Gupta", "howtodoinjava.com");
		documents.add(document1);
		writer.addDocument(config.build(document1));
		
		Document document2 = createDocument(2, "Brian", "Schultz", "example1.com");
		documents.add(document2);
		writer.addDocument(config.build(document2));
		
		Document document3 = createDocument(3, "Harish", "Bhatt", "example1.com");
		documents.add(document2);
		writer.addDocument(config.build(document3));
		
		Document document4 = createDocument(4, "Shobhna", "Pillay", "example2.com");
		documents.add(document2);
		writer.addDocument(config.build(document4));
		
		Document document5 = createDocument(5, "Jeeva", "Bhatt", "example2.com");
		documents.add(document2);
		writer.addDocument(config.build(document5));
		
		
		//writer.addDocuments(documents);
		writer.commit();
	    writer.close();
	}

	private static Document createDocument(Integer id, String firstName, String lastName, String website) 
	{
    	Document document = new Document();
//    	document.add(new StringField("id", id.toString() , Field.Store.YES));
//    	document.add(new TextField("firstName", firstName , Field.Store.YES));
//    	document.add(new TextField("lastName", lastName , Field.Store.YES));
//    	document.add(new TextField("website", website , Field.Store.YES));
    	
    	 
    	document.add(new StringField("id", id.toString() , Field.Store.YES));
    	document.add(new SortedSetDocValuesFacetField("firstName", firstName));
    	document.add(new TextField("firstName", firstName , Field.Store.YES));
    	document.add(new TextField("lastName", lastName , Field.Store.YES));
    	document.add(new TextField("website", website , Field.Store.YES));
    	document.add(new SortedSetDocValuesFacetField("website", website));
    	
    	return document;
    }

	private static IndexWriter createWriter() throws IOException 
	{
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}
}
