package vicand.finaleda.controllers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import vicand.finaleda.models.FileSearchData;
import vicand.finaleda.models.FileSimple;

public class AppControllerTests {
	
	@Test
	public void TestAppController()
	{
		String path = new File("ProyectTestFolder\\files").getAbsolutePath();
		
		//Indexar los archivos que se indexaron en FileExplorerController
		AppController.StartIndex(path);
		
		ArrayList<ArrayList<FileSearchData>> data = new ArrayList<ArrayList<FileSearchData>>();
		data = AppController.Search("demo");

		assertEquals(path +"\\text - copia (2).txt", data.get(0).get(0).getFilePath());
		assertEquals(path +"\\text - copia (5).txt", data.get(0).get(1).getFilePath());
		assertEquals(path +"\\text - copia (1).txt", data.get(0).get(2).getFilePath());
		assertEquals(path +"\\text - copia (4).txt", data.get(0).get(3).getFilePath());
		assertEquals(path +"\\text - copia (3).txt", data.get(0).get(4).getFilePath());
	}
	
}
