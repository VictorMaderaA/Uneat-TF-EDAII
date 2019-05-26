package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class FileProccessedDataTest {
	
	@Test
	public void FileProccessedDataTest() {
		
		
		//Instanciamos valores a meter
		FileSimple fs = new FileSimple("nombre", "path", "extension");
		HashMap<String, MutableInt> hm = new HashMap<String, MutableInt>();
		
		//Generamos la nueva instancia de la clase	
		FileProccessedData fp = new FileProccessedData(fs, hm);
		
		//Comprobamos
		assertEquals(fs, fp.getFile());
		assertEquals(hm, fp.getFileWords());
		
	}
		
}
