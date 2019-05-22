package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class OdfParserTests {

	@Test
	public void GetDataTest()
	{
		OdfParser parser = new OdfParser();

		String path = new File("ProyectTestFolder\\demo.odf").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}
}
