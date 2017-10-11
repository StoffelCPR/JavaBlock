package de.cpelzer.aufgaben.datum;

public class DateOutOfRangeException extends RuntimeException {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8924801242050948489L;
	
	/**
	 * 
	 * @param s
	 */
	public DateOutOfRangeException(String s) {
		super(s);
	}

}
