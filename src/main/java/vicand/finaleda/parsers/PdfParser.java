package vicand.finaleda.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

import vicand.finaleda.models.ParserData;

//.pdf
public class PdfParser implements IParser{

//	public static void main(final String[] args) throws IOException, TikaException {
//
//		BodyContentHandler handler = new BodyContentHandler();
//		Metadata metadata = new Metadata();
//		FileInputStream inputstream = new FileInputStream(new File("Example.pdf"));
//		ParseContext pcontext = new ParseContext();
//
//		// parsing the document using PDF parser
//		PDFParser pdfparser = new PDFParser();
//		pdfparser.parse(inputstream, handler, metadata, pcontext);
//
//		// getting the content of the document
//		System.out.println("Contents of the PDF :" + handler.toString());
//
//		// getting metadata of the document
//		System.out.println("Metadata of the PDF:");
//		String[] metadataNames = metadata.names();
//
//		for (String name : metadataNames) {
//			System.out.println(name + " : " + metadata.get(name));
//		}
//	}

	public ParserData GetMetadata(String filePath) {
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = null;
		
		try {
			inputstream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			//FileNotFound
			e.printStackTrace();
			//TODO - Log
			return null;
		}
		
		ParseContext pcontext = new ParseContext();
		
		PDFParser pdfparser = new PDFParser();
		
		try {
			pdfparser.parse(inputstream, handler, metadata, pcontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ParserData(handler, metadata);
	}
}
