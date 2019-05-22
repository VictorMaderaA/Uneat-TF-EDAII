package vicand.finaleda.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import vicand.finaleda.models.ParserData;

//DOC, DOCX, PPT, PPTX, XLS, XLSX, PDF, JPG, PNG, TXT
public class AutoParser implements IParser {

	final static Logger logger = Logger.getLogger(AutoParser.class);

	//https://gist.github.com/LorisBachert/7b9ac408d4564caaabef
	@Override
	public ParserData GetData(String filePath) {
		File file = new File(filePath);
		InputStream fileStream;
		try {
			fileStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			logger.warn("File not found '" + filePath + "'");
			return null;
		}
		Parser parser = new AutoDetectParser();
		Metadata metadata = new Metadata();
		BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

		TesseractOCRConfig config = new TesseractOCRConfig();
		PDFParserConfig pdfConfig = new PDFParserConfig();
		pdfConfig.setExtractInlineImages(true);

		// To parse images in files those lines are needed
		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, config);
		parseContext.set(PDFParserConfig.class, pdfConfig);
		parseContext.set(Parser.class, parser); // need to add this to make sure
		// recursive parsing happens!
		try {
			parser.parse(fileStream, handler, metadata, parseContext);
		} catch (IOException | SAXException | TikaException e) {
			logger.error("TIKA was not able to exctract text of file '" + filePath + "'", e);
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return new ParserData(handler, metadata);
	}
}
