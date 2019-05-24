package vicand.finaleda.models;

import java.util.HashMap;

public class WordMap {

	private HashMap<String, HashMap<String, Integer>> wordMap = new HashMap<String, HashMap<String, Integer>>();

	public HashMap<String, HashMap<String, Integer>> getWordMap() {
		return wordMap;
	}

	public void InsertNewFile(String word, String path, int freq) {
		// Obtenemos dentro del hashMap el key de la palabra dada
		HashMap<String, Integer> findWordHash = wordMap.get(word);
		// Revisamos si el resultado fue nulo
		if (findWordHash == null) {
			// si fue null no existe y agregamos una nueva instancia a wordMap
			HashMap<String, Integer> t = new HashMap<String, Integer>();
			t.put(path, freq);
			wordMap.put(word, t);
		} else {
			// si exite solo agregamos la ruta del archivo y la frequencia
			findWordHash.put(path, freq);
		}
	}

}
