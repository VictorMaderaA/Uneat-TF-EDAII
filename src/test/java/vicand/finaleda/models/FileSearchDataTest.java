package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;

public class FileSearchDataTest {

	@Test
	public void FileSearchDataTest() {
		// Instanciamos valores a meter
		String fs = "fs";
		double hm = 1.12;

		// Generamos la nueva instancia de la clase
		FileSearchData fsd = new FileSearchData(fs, hm);

		// Comprobamos
		assertEquals(fs, fsd.getFilePath());
		assertEquals(hm, fsd.getTf_idf(), 0);

	}

	@Test
	public void compareToTestMenor() {
		// Compara si es menor que el que pasamos por parametro

		// Instanciamos valores a meter
		String fs = "fs";
		double hm = 1.12;
		String fs1 = "fs1";
		double hm1 = 1.14;

		// Generamos la nueva instancia de la clase
		FileSearchData fsd = new FileSearchData(fs, hm);
		FileSearchData fsd1 = new FileSearchData(fs1, hm1);

		// Comparamos
		int x = fsd.compareTo(fsd1);

		// Comprobamos
		assertEquals(1, x);

	}

	@Test
	public void compareToTestMayor() {
		// Compara si es mayor que el que pasamos por parametro

		// Instanciamos valores a meter
		String fs = "fs";
		double hm = 1.12;
		String fs1 = "fs1";
		double hm1 = 1.14;

		// Generamos la nueva instancia de la clase
		FileSearchData fsd = new FileSearchData(fs, hm);
		FileSearchData fsd1 = new FileSearchData(fs1, hm1);

		// Comparamos
		int x = fsd1.compareTo(fsd);

		// Comprobamos
		assertEquals(-1, x);

	}
	
	@Test
	public void compareToTestIgual() {
		// Compara si es mayor que el que pasamos por parametro

		// Instanciamos valores a meter
		String fs = "fs";
		double hm = 1.12;
		String fs1 = "fs1";
		double hm1 = 1.12;

		// Generamos la nueva instancia de la clase
		FileSearchData fsd = new FileSearchData(fs, hm);
		FileSearchData fsd1 = new FileSearchData(fs1, hm1);

		// Comparamos
		int x = fsd1.compareTo(fsd);

		// Comprobamos
		assertEquals(0, x);

	}
	
	@Test
	public void compareToTestList() {
		// Compara si es mayor que el que pasamos por parametro

		// Generamos la nueva instancia de la clase
		FileSearchData fsd = new FileSearchData("fsd", 1);
		FileSearchData fsd1 = new FileSearchData("fsd1", 2);
		FileSearchData fsd2 = new FileSearchData("fsd2", 3);
		FileSearchData fsd3 = new FileSearchData("fsd3", 4);

		// Creamos arrayList y añadimos elementos
		ArrayList<FileSearchData> afsd= new ArrayList<FileSearchData>();
		afsd.add(fsd3);
		afsd.add(fsd1);
		afsd.add(fsd);
		afsd.add(fsd2);
		Collections.sort(afsd);

		// Comprobamos
		assertEquals(4, afsd.get(0).getTf_idf(),0);
		assertEquals(3, afsd.get(1).getTf_idf(),0);
		assertEquals(2, afsd.get(2).getTf_idf(),0);
		assertEquals(1, afsd.get(3).getTf_idf(),0);

	}

}
