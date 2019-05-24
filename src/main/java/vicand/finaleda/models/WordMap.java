package vicand.finaleda.models;

import java.util.HashMap;

public class WordMap {

	private HashMap<String, HashMap<String, Integer>> wordMap = new HashMap<String, HashMap<String, Integer>>();

	public HashMap<String, HashMap<String, Integer>> getWordMap() {
		return wordMap;
	}

	public void InsertNewFile(String word, String path, int freq) {
		HashMap<String, Integer> findWordHash = wordMap.get(word);
		if (findWordHash == null) {
			HashMap<String, Integer> t = new HashMap<String, Integer>();
			t.put(path, freq);
			wordMap.put(word, t);
		} else {
			findWordHash.put(path, freq);
		}
	}

}
