package vicand.finaleda.parsers;

import vicand.finaleda.controllers.FileExplorerController;
import vicand.finaleda.models.ParserData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

//.xlsx
public class ExcelParser implements IParser {

	final static Logger logger = Logger.getLogger(ExcelParser.class);

	public ParserData GetData(String filePath) {

		BodyContentHandler handler = new BodyContentHandler(-1);
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

		// OOXml parser
		OOXMLParser msofficeparser = new OOXMLParser();
		try {
			msofficeparser.parse(inputstream, handler, metadata, pcontext);
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

		//		System.out.println("Contents of the document:" + handler.toString());
		//		System.out.println("Metadata of the document:");
		//		String[] metadataNames = metadata.names();
		//
		//		for (String name : metadataNames) {
		//			System.out.println(name + ": " + metadata.get(name));
		//		}

		return new ParserData(handler, metadata);
	}

}
