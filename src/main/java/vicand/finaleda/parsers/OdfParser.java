package vicand.finaleda.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

	public ParserData GetMetadata(String filePath) {

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

		// Open Document Parser
		OpenDocumentParser openofficeparser = new OpenDocumentParser();

		try {
			openofficeparser.parse(inputstream, handler, metadata, pcontext);
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
