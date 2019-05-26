package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.mchange.util.AssertException;

public class SearchMapTest {

	@Test
	public void SearchWordTest() {
		String word = "word";
		String path = "path";
		double tf_idf = 1.2;

		SearchMap sm = new SearchMap();
		sm.InsertFile(word, path, tf_idf);

		ArrayList<FileSearchData> searchWord;
		searchWord = sm.SearchWord(word);

		assertEquals(tf_idf, searchWord.get(0).getTf_idf(), 0);
		assertEquals(path, searchWord.get(0).getFilePath());
	}

	@Test
	public void InsertFileTest() {
		String word = "word";
		String path = "path";
		double tf_idf = 1.2;

		SearchMap sm = new SearchMap();
		sm.InsertFile(word, path, tf_idf);

		HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();
		searchHash = sm.getSearchHash();

		assertEquals(tf_idf, searchHash.get(word).get(0).getTf_idf(), 0);
		assertEquals(path, searchHash.get(word).get(0).getFilePath());

	}

	@Test
	public void SortWordArrayTest() {

		SearchMap sm = new SearchMap();
		sm.InsertFile("a", "aa", 1.2);
		sm.InsertFile("a", "bb", 4.8);
		sm.InsertFile("a", "cc", 2.3);
		sm.InsertFile("a", "dd", 8.5);
		sm.SortWordArray("a");

		HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();
		searchHash = sm.getSearchHash();

		assertEquals(8.5, searchHash.get("a").get(0).getTf_idf(), 0);
		assertEquals(4.8, searchHash.get("a").get(1).getTf_idf(), 0);
		assertEquals(2.3, searchHash.get("a").get(2).getTf_idf(), 0);
		assertEquals(1.2, searchHash.get("a").get(3).getTf_idf(), 0);
	}

	@Test
	public void StringBuilderSerializeTest() {

		SearchMap sm = new SearchMap();
		sm.InsertFile("a", "aa", 1.2);
		sm.InsertFile("a", "bb", 4.8);
		sm.SortWordArray("a");

		HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();
		searchHash = sm.getSearchHash();

		assertEquals(4.8, searchHash.get("a").get(0).getTf_idf(), 0);
		assertEquals(1.2, searchHash.get("a").get(1).getTf_idf(), 0);
		StringBuilder sb = sm.Serialize();
		
		assertEquals("<-WORD->a<-FILE->bb<-F-DATA->4.8<-FILE->aa<-F-DATA->1.2", sb.toString());
		
		
	}
	
	@Test
	public void StringBuilderDeserializeTest() {

		SearchMap sm = new SearchMap();
		sm.InsertFile("a", "aa", 1.2);
		sm.InsertFile("a", "bb", 4.8);
		sm.SortWordArray("a");

		HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();
		searchHash = sm.getSearchHash();

		assertEquals(4.8, searchHash.get("a").get(0).getTf_idf(), 0);
		assertEquals(1.2, searchHash.get("a").get(1).getTf_idf(), 0);
		
		SearchMap sm1 = new SearchMap();
		sm1.Deserialize(new StringBuilder("<-WORD->a<-FILE->bb<-F-DATA->4.8<-FILE->aa<-F-DATA->1.2"));
		
		assertEquals(sm.Serialize().toString(), sm1.Serialize().toString());
		
		
	}
}
