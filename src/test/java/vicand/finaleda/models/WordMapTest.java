package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import com.strobel.decompiler.languages.java.ast.transforms.InsertNecessaryConversionsTransform;

public class WordMapTest {

	public void InsertNewFileTest() {
		WordMap wm = new WordMap();
		
		String word = "word";
		String path	= "path";
		int freq = 1;
		
		wm.InsertNewFile(word, path, freq);
		
		HashMap<String, HashMap<String, Integer>> wordMap = new HashMap<String, HashMap<String, Integer>>();
		
		wordMap = wm.getWordMap();
		
		assertEquals(1, wordMap.get(word).get(path),0);
	}
	
}
