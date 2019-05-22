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
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

//.xml
public class XmlParse implements IParser{

	final static Logger logger = Logger.getLogger(XmlParse.class);

	public ParserData GetData(String filePath) {

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream;
		try {
			inputstream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ParseContext pcontext = new ParseContext();

		//Xml parser
		XMLParser xmlparser = new XMLParser(); 
		try {
			xmlparser.parse(inputstream, handler, metadata, pcontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return new ParserData(handler, metadata);

	}

}
