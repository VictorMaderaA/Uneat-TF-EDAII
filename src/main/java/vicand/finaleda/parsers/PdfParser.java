package vicand.finaleda.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

import vicand.finaleda.models.ParserData;

//.pdf
public class PdfParser implements IParser{

	final static Logger logger = Logger.getLogger(PdfParser.class);

	@Override
	public ParserData GetData(String filePath) {
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = null;

		try {
			inputstream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			logger.warn("File not found '" + filePath + "'");
			return null;
		}

		ParseContext pcontext = new ParseContext();

		PDFParser pdfparser = new PDFParser();

		try {
			pdfparser.parse(inputstream, handler, metadata, pcontext);
		} catch (IOException | SAXException | TikaException e) {
			logger.warn("Could not extract text of '" + filePath + "'");
			return null;
		}

		return new ParserData(handler, metadata);
	}
}
