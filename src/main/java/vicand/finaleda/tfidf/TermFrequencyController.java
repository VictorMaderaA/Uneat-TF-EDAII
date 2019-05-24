package vicand.finaleda.tfidf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import vicand.finaleda.models.FileProccessedData;
import vicand.finaleda.models.MutableInt;
import vicand.finaleda.models.SearchMap;
import vicand.finaleda.models.TermFrequencyData;
import vicand.finaleda.models.WordMap;
import vicand.finaleda.utils.FileConector;
import vicand.finaleda.utils.Serializer;

public class TermFrequencyController {

	final static Logger logger = Logger.getLogger(TermFrequencyController.class);
	private final String FILE_NAME_SAVE = "SearchMapSave";

	WordMap wordMap;
	SearchMap searchMap;

	public TermFrequencyController() {
		logger.info("TermFrequencyController Init");
		wordMap = new WordMap();
		searchMap = new SearchMap();
		TryLoadSearchHash();
	}

	public boolean TryLoadSearchHash() {
		LoadFromFile();
		return false;
	}

	public void GetSerchTF(ArrayList<FileProccessedData> filesData) {
		PrepareData(filesData);
		FillSearchMap(wordMap.getWordMap());
		SaveToFile();
	}

	private void SaveToFile() {
		String serializedObject = searchMap.Serialize().toString();

		FileConector fileConection = new FileConector();
		fileConection.WriteToFile(FILE_NAME_SAVE, serializedObject, "txt");

	}

	private void LoadFromFile() {

		FileConector fileConection = new FileConector();
		StringBuilder fileString = fileConection.ReadFile(FILE_NAME_SAVE+".txt");
		searchMap.Deserialize(fileString);
		logger.info("Loaded SearchMap from file");

	}

	private void PrepareData(ArrayList<FileProccessedData> data) {
		for (FileProccessedData d : data) {
			String path = d.getFile().getPath();
			Iterator<Entry<String, MutableInt>> it = d.getFileWords().entrySet().iterator();

			while (it.hasNext()) {
				Entry<String, MutableInt> p = it.next();
				String word = p.getKey();
				int freq = p.getValue().get();

				wordMap.InsertNewFile(word, path, freq);
				it.remove();
			}
		}
	}

	private void FillSearchMap(HashMap<String, HashMap<String, Integer>> wordMapHash) {
		Calculations calc = new Calculations();

		Iterator<Entry<String, HashMap<String, Integer>>> itWord = wordMapHash.entrySet().iterator();
		while (itWord.hasNext()) {
			Entry<String, HashMap<String, Integer>> wordHash = itWord.next();

			String word = wordHash.getKey();
			HashMap<String, Integer> docsHash = wordHash.getValue();
			int numFilesWord = docsHash.size();

			Iterator<Entry<String, Integer>> itDocs = docsHash.entrySet().iterator();
			while (itDocs.hasNext()) {
				Entry<String, Integer> entry = itDocs.next();

				String path = entry.getKey();
				int termFreq = entry.getValue();
				double tf_idf = calc.getTf_idf(termFreq, numFilesWord);
				searchMap.InsertFile(word, path, tf_idf);
			}
			searchMap.SortWordArray(word);
		}
	}
}
