package vicand.finaleda.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MutableIntTest {

	@Test
	public void incrementTest() {
		
		MutableInt mi = new MutableInt();
		int x = 2;
		mi.increment();
		assertEquals(x, mi.get());
	}
	
	@Test
	public void getTest() {
		
		MutableInt mi = new MutableInt(4);
		int x = 4;
		
		assertEquals(x, mi.get());
	}
	
}
