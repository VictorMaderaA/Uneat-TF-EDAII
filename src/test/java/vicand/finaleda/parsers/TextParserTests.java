package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class TextParserTests {

	@Test
	public void GetDataTest()
	{
		TextParser parser = new TextParser();

		String path = new File("ProyectTestFolder\\demo.txt").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}
}
