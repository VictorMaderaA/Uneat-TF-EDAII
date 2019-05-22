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

	private final HashMap<String, Parser> extensionParser = new HashMap<String, Parser>(){
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

	public void ParseFileList(ArrayList<FileSimple> files)
	{
		for (FileSimple f : files) {
			if(!extensionParser.containsKey(f.getExtension())) continue;

			IParser parser = ParserFactory.GetParser(extensionParser.get(f.getExtension()));
			ParseDataProcess(f, parser);
		}

		logger.info("Finished With Parser of File List ");

		for (FileProccessedData f : filesProccessedData) {
			Iterator it = f.getFileWords().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, MutableInt> pair = (Map.Entry<String, MutableInt>)it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue().get());
				it.remove(); // avoids a ConcurrentModificationException
			}
		}
	}

	private void ParseDataProcess(FileSimple f, IParser parser)
	{
		ParserData data =  parser.GetData(f.getPath());
		if(data == null) 
		{
			logger.info("Will skip file " + f.getPath() + "returned null data");
			return;
		}
		logger.info("--- Starting ProccesContent for file " + f.getPath());

		Map<String, MutableInt> wordHash = ProccessContentWords(data.contentHandler);
		if(wordHash == null)
		{
			logger.info("word hash returned null");
		}
		FileProccessedData proccessedData = new FileProccessedData(f.getName(), f.getPath(), wordHash);
		filesProccessedData.add(proccessedData);
		logger.info("--- Finished ProccesContent for file " + f.getPath());

	}

	private Map<String, MutableInt> ProccessContentWords(BodyContentHandler content)
	{
		String textContent = content.toString();
		if (textContent.isEmpty()) {
			logger.debug("File is empty");
			return null;
		} 


		Map<String, MutableInt> wordsHash = new HashMap<String, MutableInt>();
		String[] words = textContent.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			String w = words[i] = words[i].replaceAll("[^\\w]", "");
			MutableInt count = wordsHash.get(w);
			if(count == null)
				wordsHash.put(w, new MutableInt());
			else
				count.increment();
		}
		return wordsHash;
	}


}
