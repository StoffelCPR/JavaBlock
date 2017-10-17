package de.cpelzer.aufgaben.datum;

/**
 * DateOutOfRangeException
 * 
 * @author Christoph Pelzer on 11.10.17
 *
 */
public class DateOutOfRangeException extends RuntimeException {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8924801242050948489L;

	/**
	 * Constructor /w super constructor
	 * 
	 * @param s
	 *            The string which is printed to the Console when the Exception is
	 *            thrown
	 */
	public DateOutOfRangeException(String s) {
		super(s);
	}

}
