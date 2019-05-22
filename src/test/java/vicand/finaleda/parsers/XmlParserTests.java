package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class XmlParserTests {

	@Test
	public void GetDataTest()
	{
		XmlParse parser = new XmlParse();

		String path = new File("ProyectTestFolder\\demo.xml").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}
}
