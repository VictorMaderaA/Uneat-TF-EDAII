package vicand.finaleda.tfidf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculationsTest {

	@Test
	public void getTf_idf() {
		
		Calculations c = new Calculations();
		
		int numDocumentos = 4;
		int termFreq = 2;

		
		assertEquals(1.2041199826559248, c.getTf_idf(numDocumentos, termFreq),0);
	}
	
	
}
