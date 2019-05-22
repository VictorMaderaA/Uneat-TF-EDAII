package vicand.finaleda.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.metadata.Metadata;

import org.xml.sax.SAXException;

import vicand.finaleda.models.ParserData;

//.odp
public class OdfParser implements IParser {

	final static Logger logger = Logger.getLogger(OdfParser.class);

	@Override
	public ParserData GetData(String filePath) {

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

		// Open Document Parser
		OpenDocumentParser openofficeparser = new OpenDocumentParser();

		try {
			openofficeparser.parse(inputstream, handler, metadata, pcontext);
		} catch (IOException | SAXException | TikaException e) {
			logger.warn("Could not extract text of '" + filePath + "'");
			return null;
		}

		return new ParserData(handler, metadata);
	}

}
