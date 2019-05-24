package vicand.finaleda.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import vicand.finaleda.controllers.TermFrequencyController;

public class SearchMap implements Serializable {

	private static final long serialVersionUID = 1500237931168149408L;
	final static Logger logger = Logger.getLogger(SearchMap.class);

	HashMap<String, ArrayList<FileSearchData>> searchHash = new HashMap<String, ArrayList<FileSearchData>>();

	public HashMap<String, ArrayList<FileSearchData>> getSearchHash() {
		return searchHash;
	}

	public ArrayList<FileSearchData> SearchWord(String word) {
		// buscamos resultados de una palabra dada
		ArrayList<FileSearchData> fileSearchDataArray = searchHash.get(word);
		// si es null no se encontro la palabra return null
		if (fileSearchDataArray == null) {
			logger.info("Did not find any data for word " + word);
			return null;
		}

		// devolvemos los resultados
		return fileSearchDataArray;
	}

	public void ClearSearchMap() {
		searchHash.clear();
	}

	public void InsertFile(String word, String path, Double tf_idf) {
		// Obtenemos del searchHash el valor de una palabra
		ArrayList<FileSearchData> findWord = searchHash.get(word);
		// Creamos el FileSearchData de la palabra
		FileSearchData fsd = new FileSearchData(path, tf_idf);

		// Revisamos si el resultado de serchHash es nullo
		if (findWord == null) {
			// si es nullo agregamos una nueva palabra al hash con su info de archivo
			ArrayList<FileSearchData> t = new ArrayList<FileSearchData>();
			t.add(fsd);
			searchHash.put(word, t);
		} else {
			// si existe a esa palabra le agregamos informacion de archivo
			findWord.add(fsd);
		}
	}

	public void SortWordArray(String word) {
		// Obtenemos el arrayList de archivos de una palabra
		ArrayList<FileSearchData> array = searchHash.get(word);
		// Lo ordenamos por tf-idf mayor a menor
		Collections.sort(array);
	}

	public StringBuilder Serialize() {
		// Iniciamos la variable donde guardaremos la info serializada
		StringBuilder serialized = new StringBuilder();

		// Creamos el iterador para poder recorrer el serachHash
		Iterator<Entry<String, ArrayList<FileSearchData>>> it = new HashMap<String, ArrayList<FileSearchData>>(
				searchHash).entrySet().iterator();

		// Recorremos serachHash
		while (it.hasNext()) {
			// Obtenemos el elemento en el que vamos iterando
			Entry<String, ArrayList<FileSearchData>> pair = (Entry<String, ArrayList<FileSearchData>>) it.next();
			// Obtenemos la llave que es la palabra
			String word = pair.getKey();
			// Obtenemos los FileSearchData de la palabra
			ArrayList<FileSearchData> filesData = pair.getValue();

			// Iniciamos la variable donde guardar la info de los archivos
			StringBuilder filesDataString = new StringBuilder();

			// recorremos todos los archivos de la palabra
			for (FileSearchData fd : filesData) {
				// Creamos la version serializada de el archivo y lo agregamos a la info de los
				// archivos
				filesDataString.append("<-FILE->" + fd.getFilePath() + "<-F-DATA->" + fd.getTf_idf());
			}

			// agregamos la palabra con su info de archivos a la variable serializada
			serialized.append("<-WORD->" + word + filesDataString);

			it.remove();
		}

		return serialized;
	}

	public void Deserialize(StringBuilder dataString) {
		logger.info("Starting Deserialization");
		searchHash = new HashMap<String, ArrayList<FileSearchData>>();

		// Separa el string para cada Palabra
		String[] wordsArray = dataString.toString().split("<-WORD->");

		// Para cada parabla que se encuentre
		for (String wordData : wordsArray) {
			// Si el string esta vacio
			if (wordData.isBlank())
				continue;

			// Separamos los archivos de esa palabra
			String[] data = wordData.split("<-FILE->");

			// guardamos aparte la palabra que estamos actualmente
			String word = data[0];

			// Creamos el array donde guardaremos los archivos en los que estamos
			ArrayList<FileSearchData> fileSerachData = new ArrayList<FileSearchData>();

			// Recorremos el array para cada archivo que se encuentra guardado
			for (int i = 1; i < data.length; i++) {

				try {
					// Separamos la informacion de el archivo
					String[] fileData = data[i].split("<-F-DATA->");
					// Guardamos temporalmente la ruta y tf-idf del archivo
					String path = fileData[0];
					double tfidf = Double.parseDouble(fileData[1]);

					// Agregamos un nuevo objecto de informacion al array de archivos de palabra
					fileSerachData.add(new FileSearchData(path, tfidf));
				} catch (Exception e) {
					logger.warn("Failed to recover FileData for word " + word);
				}

			}

			// Agregamos al hash la palabra con el array de los archivos que generamos
			searchHash.put(word, fileSerachData);
		}

		logger.info("Finished Deserialization");
	}

}
