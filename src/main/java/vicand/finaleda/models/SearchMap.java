package vicand.finaleda.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SearchMap implements Serializable {

	private static final long serialVersionUID = 1500237931168149408L;
	
	HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();

	public HashMap<String, ArrayList<FileSearchData>> getSearchHash() {
		return searchHash;
	}
	
	public void InsertFile(String word, String path, Double tf_idf)
	{
		ArrayList<FileSearchData> findWord = searchHash.get(word);
		FileSearchData fsd = new FileSearchData(path, tf_idf);
		if(findWord == null)
		{
			ArrayList<FileSearchData> t = new ArrayList<FileSearchData>();
			t.add(fsd);
			searchHash.put(word, t);
		}
		else
		{
			findWord.add(fsd);
		}
	}
	
	public void Serialize()
	{
	    Iterator<Entry<String, ArrayList<FileSearchData>>> it = searchHash.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, ArrayList<FileSearchData>> pair = (Entry<String, ArrayList<FileSearchData>>)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	

//	HashMap<String, HashMap<String, Double>> searchHash = new HashMap<String, HashMap<String,Double>>();
//
//	public HashMap<String, HashMap<String, Double>> getSearchHash() {
//		return searchHash;
//	}
//	
//	public void InsertFile(String word, String path, Double tf_idf)
//	{
//		HashMap<String, Double> findWordHash = searchHash.get(word);
//		if(findWordHash == null)
//		{
//			HashMap<String, Double> t = new HashMap<String, Double>();
//			t.put(path, tf_idf);
//			searchHash.put(word, t);
//		}
//		else
//		{
//			findWordHash.put(path, tf_idf);
//		}
//	}
	
}
