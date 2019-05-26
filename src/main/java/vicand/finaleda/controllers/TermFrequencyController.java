package vicand.finaleda.controllers;

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
import vicand.finaleda.models.FileSearchData;
import vicand.finaleda.models.MutableInt;
import vicand.finaleda.models.SearchMap;
import vicand.finaleda.models.WordMap;
import vicand.finaleda.tfidf.Calculations;
import vicand.finaleda.utils.FileConector;

public class TermFrequencyController {

	final static Logger logger = Logger.getLogger(TermFrequencyController.class);
	private final String FILE_NAME_SAVE = "SearchMapSave";

	WordMap wordMap;
	SearchMap searchMap;

	public TermFrequencyController() {
		logger.info("TermFrequencyController Init");
		wordMap = new WordMap();
		searchMap = new SearchMap();
	}

	public boolean TryLoadSearchHash() {
		return LoadFromFile();
	}

	public void GetSerchTF(ArrayList<FileProccessedData> filesData) {
		// Limpiamos en serchMap para nuevos archivos
		searchMap.ClearSearchMap();

		// preparamos la informacion para calcular
		PrepareData(filesData);
		// LLenamos el searchMap con las palabras ya mapeadas y listas para calcular
		FillSearchMap(wordMap.getWordMap());
		// salvamos el searchMap a un archivo
		SaveToFile();
	}

	public ArrayList<ArrayList<FileSearchData>> Search(String searchString) {
		// separamos las palabras a buscar
		String[] words = searchString.split(" ");
		// creamos el array de resultados de archivos para las palabras
		ArrayList<ArrayList<FileSearchData>> searchDataList = new ArrayList<ArrayList<FileSearchData>>();
		// para cada palabra
		for (String word : words) {
			// obtenemos la informacion de los archivos de palabra buscada
			ArrayList<FileSearchData> d = searchMap.SearchWord(word);
			// si es null continuamos
			if (d == null)
				continue;
			// agregamos los resultados output
			searchDataList.add(d);
		}
		// devolvemos output
		return searchDataList;
	}

	private void SaveToFile() {
		// serializamos el serchMap
		String serializedObject = searchMap.Serialize().toString();
		// Inicializamos un nuevo FileConector
		FileConector fileConection = new FileConector();
		// Escribimos en el archivo la informacion serializada
		fileConection.WriteToFile(FILE_NAME_SAVE, serializedObject, "txt");

	}

	private boolean LoadFromFile() {
		searchMap.ClearSearchMap();
		FileConector fileConection = new FileConector();
		StringBuilder fileString = fileConection.ReadFile(FILE_NAME_SAVE + ".txt");
		if (fileString == null) {
			logger.info("Coould not load Search Data File");
			return false;
		}
		searchMap.Deserialize(fileString);
		logger.info("Loaded SearchMap from file");
		return true;

	}

	private void PrepareData(ArrayList<FileProccessedData> data) {
		// para cada archivo
		for (FileProccessedData d : data) {
			// obtenemos el path del arhivo
			String path = d.getFile().getPath();
			// generamos el iterador de cada palabra en el archivo
			Iterator<Entry<String, MutableInt>> it = d.getFileWords().entrySet().iterator();

			// para cada palabra
			while (it.hasNext()) {
				// obtenemos la entidad de el palabra frequencia
				Entry<String, MutableInt> p = it.next();
				// obtenemos la palabra
				String word = p.getKey().toLowerCase();
				// obtenemos la fecuencia
				int freq = p.getValue().get();

				// insertamos en wordMap la palabra con su ruta y frequencia
				wordMap.InsertNewFile(word, path, freq);
				it.remove();
			}
		}
	}

	private void FillSearchMap(HashMap<String, HashMap<String, Integer>> wordMapHash) {
		// iniciamos el objeto para calcular el tfidf
		Calculations calc = new Calculations();
		// creamos el iterador para cada palabra
		Iterator<Entry<String, HashMap<String, Integer>>> itWord = wordMapHash.entrySet().iterator();
		// para cada palabra
		while (itWord.hasNext()) {
			Entry<String, HashMap<String, Integer>> wordHash = itWord.next();
			// obtenemos la palabra
			String word = wordHash.getKey();
			// obtenemos la informacion de rutas y frequencua en el archivo
			HashMap<String, Integer> docsHash = wordHash.getValue();
			// obtenemos el numero de archivos en los que aparece la palabra
			int numFilesWord = docsHash.size();

			// creamos el iterador de los archivos donde sale la palabra
			Iterator<Entry<String, Integer>> itDocs = docsHash.entrySet().iterator();
			// Para cada archivo
			while (itDocs.hasNext()) {
				Entry<String, Integer> entry = itDocs.next();
				// obtenemos el path del archivo
				String path = entry.getKey();
				// obtenemos la frequencua
				int termFreq = entry.getValue();
				// obtenemos el tf_idf
				double tf_idf = calc.getTf_idf(termFreq, numFilesWord);
				// insertamos en el tf_idf la palabra con ruta y tfidf
				searchMap.InsertFile(word, path, tf_idf);
				itDocs.remove();
			}
			// sorteamos la lsita dentro de serchMap de la palabra
			searchMap.SortWordArray(word);
			itWord.remove();
		}
		logger.info("Finished Filling Search map with indexed data");
	}
}
