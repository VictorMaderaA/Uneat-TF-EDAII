package vicand.finaleda.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

	public void InsertFile(String word, String path, Double tf_idf) {
		ArrayList<FileSearchData> findWord = searchHash.get(word);
		FileSearchData fsd = new FileSearchData(path, tf_idf);
		if (findWord == null) {
			ArrayList<FileSearchData> t = new ArrayList<FileSearchData>();
			t.add(fsd);
			searchHash.put(word, t);
		} else {
			findWord.add(fsd);
		}
	}

	public void SortWordArray(String word) {
		ArrayList<FileSearchData> array = searchHash.get(word);
		Collections.sort(array);
	}

	public StringBuilder Serialize() {
		StringBuilder serialized = new StringBuilder();

		Iterator<Entry<String, ArrayList<FileSearchData>>> it = searchHash.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, ArrayList<FileSearchData>> pair = (Entry<String, ArrayList<FileSearchData>>) it.next();
			String word = pair.getKey();
			ArrayList<FileSearchData> filesData = pair.getValue();

			StringBuilder filesDataString = new StringBuilder();

			for (FileSearchData fd : filesData) {
				filesDataString.append("<-FILE->" + fd.getFilePath() + "<-F-DATA->" + fd.getTf_idf());
			}

			serialized.append("<-WORD->" + word + filesDataString);

			it.remove();
		}

		return serialized;
	}
	
	public void Deserialize(StringBuilder dataString)
	{
		
	}

}
