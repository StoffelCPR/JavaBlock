package de.cpelzer.aufgaben.datum;

public class InvalidDateException extends RuntimeException {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -1785712778624775566L;

	/**
	 * InvalidDateExcetion should be thrown when the Date DD/MM/YY is an impossible combination
	 * @param s	takes the String s as argument for the thrown exception
	 */
	public InvalidDateException(String s) {
		super(s);
	}

}
