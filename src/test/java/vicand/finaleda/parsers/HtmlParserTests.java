package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class HtmlParserTests {

	@Test
	public void GetDataTest()
	{
		HtmlParser parser = new HtmlParser();

		String path = new File("ProyectTestFolder\\demo.html").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}
}
