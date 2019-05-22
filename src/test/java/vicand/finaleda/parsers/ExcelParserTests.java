package vicand.finaleda.parsers;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import vicand.finaleda.models.ParserData;

public class ExcelParserTests {

	@Test
	public void GetDataTest()
	{
		ExcelParser parser = new ExcelParser();

		String path = new File("ProyectTestFolder\\demo.xlsx").getAbsolutePath();

		ParserData data = parser.GetData(path);

		assertNotNull(data);
		assertNotNull(data.contentHandler);
		assertNotNull(data.metadata);
	}

}
