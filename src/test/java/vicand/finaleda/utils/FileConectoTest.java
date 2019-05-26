package vicand.finaleda.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileConectoTest {

	@Test
	public void WriteToFileTest() {
		FileConector fc = new FileConector();
		
		String fn ="fileName";
		String d = "data";
		
		fc.WriteToFile("fileName","data");
		StringBuilder s = fc.ReadFile(fn+ ".txt");
		
		assertEquals(d+"\n", s.toString());
	
	}
	
}
