package de.cpelzer.aufgaben.datum;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatumTest {

	@Test
	public void testGetMonatslaenge() {
		assertEquals(31, Datum.getMonatslaenge(1, 2017));
		assertEquals(28, Datum.getMonatslaenge(2, 2017));
		assertEquals(29, Datum.getMonatslaenge(2, 2016));
		assertEquals(31, Datum.getMonatslaenge(3, 2017));
		assertEquals(30, Datum.getMonatslaenge(4, 2017));
		assertEquals(31, Datum.getMonatslaenge(5, 2017));
		assertEquals(30, Datum.getMonatslaenge(6, 2017));
		assertEquals(31, Datum.getMonatslaenge(7, 2017));
		assertEquals(31, Datum.getMonatslaenge(8, 2017));
		assertEquals(30, Datum.getMonatslaenge(9, 2017));
		assertEquals(31, Datum.getMonatslaenge(10, 2017));
		assertEquals(30, Datum.getMonatslaenge(11, 2017));
		assertEquals(31, Datum.getMonatslaenge(12, 2017));
	}

	@Test
	public void testIsSchaltjahr() {
		assertEquals(true, Datum.isSchaltjahr(2016));
		assertEquals(false, Datum.isSchaltjahr(2017));

	}

	@Test
	public void testEquals() {
		assertEquals(true, new Datum(5, 5, 2016).equals(new Datum(5, 5, 2016)));
		assertEquals(false, new Datum(5, 5, 2016).equals(new Datum(6, 5, 2016)));
	}
	@Test
	public void testIsGleicherTag() {
		assertEquals(true, new Datum(5,5,2016).isGleicherTag(new Datum(5,5,1997)));
		assertEquals(true, new Datum(5,5,2016).isGleicherTag(new Datum(5,5,2016)));
		assertEquals(false, new Datum(5,5,2016).isGleicherTag(new Datum(4,5,1997)));
		assertEquals(false, new Datum(5,5,2016).isGleicherTag(new Datum(6,5,2016)));
	}
	@Test
	public void testToString() {
		assertEquals("5/5/2017", new Datum(5,5,2017).toString());
	}
	
	@Test (expected = InvalidDateException.class)
	public void testDatum() {
		new Datum(35,2,2016);
	}
	@Test (expected = DateOutOfRangeException.class)
	public void testDatum1() {
		new Datum(17,5,2150);
		
	}
}
