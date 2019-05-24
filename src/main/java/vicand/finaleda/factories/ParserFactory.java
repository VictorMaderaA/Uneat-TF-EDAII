package vicand.finaleda.factories;

import vicand.finaleda.enums.Parser;
import vicand.finaleda.parsers.*;

public class ParserFactory {

	public static IParser GetParser(Parser type) {
		IParser returnValue = null;
		switch (type) {
		case AUTO:
			returnValue = new AutoParser();
			break;
		case EXCEL:
			returnValue = new ExcelParser();
			break;
		case HTML:
			returnValue = new HtmlParser();
			break;
		case ODF:
			returnValue = new OdfParser();
			break;
		case PDF:
			returnValue = new PdfParser();
			break;
		case TEXT:
			returnValue = new TextParser();
			break;
		case XML:
			returnValue = new XmlParse();
			break;

		default:
			break;
		}

		return returnValue;
	}

}
