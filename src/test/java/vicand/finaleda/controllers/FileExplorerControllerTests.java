package vicand.finaleda.controllers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import vicand.finaleda.models.FileSimple;

public class FileExplorerControllerTests {

	@Test
	public void GetFilesInFolder()
	{
		FileExplorerController controller = new FileExplorerController();

		String path = new File("ProyectTestFolder\\files").getAbsolutePath();
		ArrayList<FileSimple> files = controller.GetFilesInFolder(path);

		assertEquals(5, files.size());
		int i = 1;
		for (FileSimple f : files) {
			assertEquals("text - copia (" + i + ").txt", f.getName());
			assertEquals(".txt", f.getExtension());
			i++;
		}
	}

}
