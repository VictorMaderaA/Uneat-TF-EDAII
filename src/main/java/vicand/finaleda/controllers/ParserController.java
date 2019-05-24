package vicand.finaleda.controllers;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tika.sax.BodyContentHandler;

import vicand.finaleda.enums.Parser;
import vicand.finaleda.factories.ParserFactory;
import vicand.finaleda.models.FileProccessedData;
import vicand.finaleda.models.FileSimple;
import vicand.finaleda.models.MutableInt;
import vicand.finaleda.models.ParserData;
import vicand.finaleda.parsers.AutoParser;
import vicand.finaleda.parsers.IParser;

public class ParserController {

	final static Logger logger = Logger.getLogger(ParserController.class);
	ArrayList<FileProccessedData> filesProccessedData = new ArrayList<FileProccessedData>();

	private final HashMap<String, Parser> extensionParser = new HashMap<String, Parser>() {
		{
			put(".doc", Parser.AUTO);
			put(".docx", Parser.AUTO);
			put(".ppt", Parser.AUTO);
			put(".pptx", Parser.AUTO);
			put(".xls", Parser.AUTO);
			put(".xlsx", Parser.AUTO);
			put(".pdf", Parser.AUTO);
			put(".jpg", Parser.AUTO);
			put(".png", Parser.AUTO);
			put(".txt", Parser.AUTO);

			put(".html", Parser.HTML);
			put(".odp", Parser.ODF);
		}
	};

	public ArrayList<FileProccessedData> ParseFileList(ArrayList<FileSimple> files) {
		// Para cada archivo en el arrayList
		for (FileSimple f : files) {
			// revisamos si su extension la podemos parsear si no continuamos con el
			// siguiente
			if (!extensionParser.containsKey(f.getExtension()))
				continue;

			// Obtenemos el parser del archivo actual
			IParser parser = ParserFactory.GetParser(extensionParser.get(f.getExtension()));
			// Parseamos el archivo
			ParseDataProcess(f, parser);
		}

		logger.info("Finished With Parser of File List ");

		// devolvemos la lista de datos procesados
		return filesProccessedData;
	}

	private void ParseDataProcess(FileSimple f, IParser parser) {
		// Obtenemos la informacion del archivo
		ParserData data = parser.GetData(f.getPath());

		// Si la informacion es nula posiblemente fue un error y devolvemo null
		if (data == null) {
			logger.info("Will skip file " + f.getPath() + "returned null data");
			return;
		}

		// Iniciamos a procesar el contenido del archivo
		logger.info("--- Starting ProccesContent for file " + f.getPath());

		// Procesamos las palabras del archivo y las guardamos en el hash
		Map<String, MutableInt> wordHash = ProccessContentWords(data.contentHandler);

		// si el word hash retorna null notificamos
		if (wordHash == null) {
			logger.info("word hash returned null");
		}
		// creamos una nueva instancia de FileProccessedData
		FileProccessedData proccessedData = new FileProccessedData(f, wordHash);

		// guardamos la info del archivo
		filesProccessedData.add(proccessedData);

		// termina el parser de el archivo
		logger.info("--- Finished ProccesContent for file " + f.getPath());

	}

	private Map<String, MutableInt> ProccessContentWords(BodyContentHandler content) {
		// Guardamos el contenido del archivo en una string
		String textContent = content.toString();
		// Si la string esta vacia devolvemos null
		if (textContent.isEmpty()) {
			logger.debug("File is empty");
			return null;
		}

		// creamos el hash de la palabra con sus repeticiones
		Map<String, MutableInt> wordsHash = new HashMap<String, MutableInt>();
		// dividimos el texto en las palabras
		String[] words = textContent.split("\\s+");

		// para cada palabra
		for (int i = 0; i < words.length; i++) {

			// remplazamos caracteres especiales por vacio
			String w = words[i] = words[i].replaceAll("[^\\w]", "");

			// si el resultado es vacio continuamos con la sig palabra
			if (w.isBlank())
				continue;

			// Obtenemos del word hash la frequencia de la palabra
			MutableInt count = wordsHash.get(w);

			// revisamos si el contador es nulo
			if (count == null)
				// si la frequencua es null no exite en el word hash y lo agregamos
				wordsHash.put(w, new MutableInt());
			else
				// si existe aumentamos uno a la frequencia de esa palabra
				count.increment();
		}
		// devolvemos el word hash
		return wordsHash;
	}

}
