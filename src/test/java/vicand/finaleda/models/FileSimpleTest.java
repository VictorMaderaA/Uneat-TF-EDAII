package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class FileSimpleTest {
	
	@Test
	public void getSetNameTest() {
		FileSimple fs = new FileSimple(null, null, null);
		fs.setName("name");
		
		assertEquals("name", fs.getName());
		
	}
	
	@Test
	public void getSetExtentionTest() {
		FileSimple fs = new FileSimple(null, null, null);
		fs.setExtension("Extention");
		
		assertEquals("Extention", fs.getExtension());
		
	}
	
	@Test
	public void getSetPathTest() {
		FileSimple fs = new FileSimple(null, null, null);
		fs.setPath("path");
		
		assertEquals("path", fs.getPath());
		
	}
	
	
	@Test
	public void FileSimpleTest() {
		FileSimple fs = new FileSimple("Nombre", "Path", "Extension");
		
		assertEquals("Nombre", fs.getName());
		assertEquals("Path", fs.getPath());
		assertEquals("Extension", fs.getExtension());
		
	}
	
	@Test
	public void FileSimpleFileTest() {
		File path = new File("ProyectTestFolder\\files\\text - copia (2).txt");
		
		FileSimple fs = new FileSimple(path);
		
		assertEquals("text - copia (2).txt", fs.getName());
		assertEquals(path.getAbsolutePath(), fs.getPath());
		assertEquals(".txt", fs.getExtension());
		
	}
	
}
