package vicand.finaleda.controllers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import vicand.finaleda.models.FileProccessedData;
import vicand.finaleda.models.FileSimple;

public class ParserControllerTest {

	@Test
	public void ParseListTest() {

		//Obtenemos la ruta
		String path = new File("ProyectTestFolder\\files").getAbsolutePath();
		//Instanciamos la clase
		ParserController ps = new ParserController();

		//Creamos el objeto que mandamos
		ArrayList<FileSimple> fs = new ArrayList<FileSimple>();
		//Agregamos al objeto
		fs.add(new FileSimple(new File(path + "\\text - copia (2).txt")));
		//Creamos el objeto que obtendremos
		ArrayList<FileProccessedData> filesProccessedData = new ArrayList<FileProccessedData>();
		//Llenamos el objeto
		filesProccessedData = ps.ParseFileList(fs);
		//Obtenemos el primer elemento
		FileProccessedData f = filesProccessedData.get(0);
		//Comprobamos que tenga 1
		assertEquals(1, f.getFileWords().get("demo").get());
	}

}
