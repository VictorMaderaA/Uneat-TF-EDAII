package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class PdfParserTests {

	@Test
	public void GetDataTest()
	{
		PdfParser parser = new PdfParser();

		String path = new File("ProyectTestFolder\\demo.pdf").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}
}
