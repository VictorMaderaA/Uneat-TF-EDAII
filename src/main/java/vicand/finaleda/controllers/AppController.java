package vicand.finaleda.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import vicand.finaleda.models.FileProccessedData;
import vicand.finaleda.models.FileSearchData;
import vicand.finaleda.models.FileSimple;

public class AppController {

	final static Logger logger = Logger.getLogger(AppController.class);

	private static TermFrequencyController tfc = new TermFrequencyController();
	private static boolean DataLoaded = false;

	public static void TryToLoadSearchData() {
		DataLoaded = tfc.TryLoadSearchHash();

		if (DataLoaded)
			logger.info("Loaded Search Data");
		else
			logger.info("Did not load Search Data");
	}

	public static void StartIndex(String folderPath) {
		if (folderPath.isBlank()) {
			logger.trace("Arg: folderPath was empty.");
			return;
		}

		logger.info("Starting Indexing for path: " + folderPath);

		// Inicializamos Controlador de archivos
		FileExplorerController fileExplController = new FileExplorerController();
		// Obtenemos los archivos de ña ruta especificada
		ArrayList<FileSimple> files = fileExplController.GetFilesInFolder(folderPath);

		// Procesamos los archivos obtenidos y los guardamos la informacion procesada
		ArrayList<FileProccessedData> proccessedFileData = new ParserController().ParseFileList(files);

		// Calculamos el tfidf de los archivos procesados
		tfc.GetSerchTF(proccessedFileData);

		DataLoaded = true;
	}

	public static ArrayList<ArrayList<FileSearchData>> Search(String searchString) {
		return tfc.Search(searchString.toLowerCase());
	}

}
