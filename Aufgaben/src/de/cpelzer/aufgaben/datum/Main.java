package de.cpelzer.aufgaben.datum;

public class Main {

	public static void main(String[] args) {
		Datum d1 = new Datum(330, 2017);
		System.out.println("Das ist D1 " + d1);
		
		System.out.println("Von d1 is tmorgen "  + d1.morgen());
		System.out.println("Von d1 war gestern " + d1.gestern());
		
		Datum d2 = new Datum(29,2,2016);
		
		System.out.println("d2 morgen " + d2.morgen());
		System.out.println("d2 gestern " + d2.gestern());
		
		System.out.println(d2);
		System.out.println(d1.equals(d2));
		System.out.println(d1.isGleicherTag(d2));
		

	}

}
