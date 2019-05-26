package vicand.finaleda.factories;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vicand.finaleda.enums.Parser;
import vicand.finaleda.parsers.AutoParser;
import vicand.finaleda.parsers.ExcelParser;
import vicand.finaleda.parsers.HtmlParser;
import vicand.finaleda.parsers.IParser;
import vicand.finaleda.parsers.OdfParser;
import vicand.finaleda.parsers.PdfParser;
import vicand.finaleda.parsers.TextParser;
import vicand.finaleda.parsers.XmlParse;

public class ParserFactoryTests {

	@Test
	public void GetParserExcel() 
	{
		IParser parser = ParserFactory.GetParser(Parser.EXCEL);
		assertEquals(ExcelParser.class, parser.getClass());
	}

	@Test
	public void GetParserHtml() 
	{
		IParser parser = ParserFactory.GetParser(Parser.HTML);
		assertEquals(HtmlParser.class, parser.getClass());
	}

	@Test
	public void GetParserOdf() 
	{
		IParser parser = ParserFactory.GetParser(Parser.ODF);
		assertEquals(OdfParser.class, parser.getClass());
	}

	@Test
	public void GetParserPdf() 
	{
		IParser parser = ParserFactory.GetParser(Parser.PDF);
		assertEquals(PdfParser.class, parser.getClass());
	}

	@Test
	public void GetParserText() 
	{
		IParser parser = ParserFactory.GetParser(Parser.TEXT);
		assertEquals(TextParser.class, parser.getClass());
	}

	@Test
	public void GetParserXml() 
	{
		IParser parser = ParserFactory.GetParser(Parser.XML);
		assertEquals(XmlParse.class, parser.getClass());
	}

	@Test
	public void GetParseAuto() 
	{
		IParser parser = ParserFactory.GetParser(Parser.AUTO);
		assertEquals(AutoParser.class, parser.getClass());
	}
}
