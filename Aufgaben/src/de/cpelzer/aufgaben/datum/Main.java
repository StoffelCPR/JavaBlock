package de.cpelzer.aufgaben.datum;

/**
 * test class main
 * @author Christoph Pelzer 11.10.17
 *
 */
public class Main {

	public static void main(String[] args) {
		Datum d1 = new Datum(60, 2016);
		
		System.out.println(d1.toString());
		
		
		Datum d2 = new Datum(59, 2017);
		System.out.println(d2.toString());
		
		Datum d3 = new Datum(60, 2017);
		System.out.println(d3.toString());
	}

}
