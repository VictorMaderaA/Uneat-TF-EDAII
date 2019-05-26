package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserDataTest {

	
	@Test
	public void ParserDataTest() {
		ParserData pd = new ParserData(null, null);
		
		assertEquals(null, pd.contentHandler);
		assertEquals(null, pd.metadata);
		
	}
}
