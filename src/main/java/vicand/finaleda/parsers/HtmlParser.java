package vicand.finaleda.parsers;

import vicand.finaleda.models.ParserData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

//.html
public class HtmlParser implements IParser{

	final static Logger logger = Logger.getLogger(HtmlParser.class);

	@Override
	public ParserData GetData(String filePath) {
		//detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream;
		try {
			inputstream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			logger.warn("File not found '" + filePath + "'");
			return null;
		}
		ParseContext pcontext = new ParseContext();

		//Html parser 
		org.apache.tika.parser.html.HtmlParser htmlparser = new org.apache.tika.parser.html.HtmlParser();

		try {
			htmlparser.parse(inputstream, handler, metadata,pcontext);
		}catch (IOException | SAXException | TikaException e) {
			logger.warn("Could not extract text of '" + filePath + "'");
			return null;
		}

		return new ParserData(handler, metadata);


	}

}
