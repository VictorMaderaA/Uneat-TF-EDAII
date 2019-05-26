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
		
		//Inicio fileControler
		FileExplorerController controller = new FileExplorerController();
		//Le doy un archivo y obtengo su ruta
		String path = new File("ProyectTestFolder\\files").getAbsolutePath();
		ArrayList<FileSimple> files = controller.GetFilesInFolder(path);
		//Compruebo que haya la misma cantidad de archivos ern ambos
		assertEquals(5, files.size());
		int i = 1;
		for (FileSimple f : files) {
			//Compruebo que los nombres sean iguales
			assertEquals("text - copia (" + i + ").txt", f.getName());
			assertEquals(".txt", f.getExtension());
			i++;
		}
	}

}
