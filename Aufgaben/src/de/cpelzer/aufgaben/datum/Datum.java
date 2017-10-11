package de.cpelzer.aufgaben.datum;

/**
 * Datum class
 * 
 * @author Christoph Pelzer on 11.10.17
 *
 */
public class Datum {
	/**
	 * the length of each month
	 */
	public static final int[] monatslaengen = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * the name of the days of a week
	 */
	public static final String[] wochentage = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };

	/**
	 * holds the values for "Datum"
	 */
	private int tag, monat, jahr;

	/**
	 * MonatsLaenge (the length of a month)
	 * 
	 * @param monat
	 *            takes one month as parameter
	 * @param jahr
	 *            takes the year as parameter
	 * @return returns the length of the month.
	 */
	public static int getMonatslaenge(int monat, int jahr) {
		if (monat == 2) {
			if (isSchaltjahr(jahr)) {
				return 29;
			}
		}
		return monatslaengen[monat - 1];
	}

	/**
	 * Checks if a year is leap-year
	 * 
	 * @param jahr
	 *            takes the year as parameter
	 * @return returns true if the year is a leap year/false if it's not
	 */
	public static boolean isSchaltjahr(int jahr) {
		if (jahr % 4 == 0 && jahr % 100 != 0 || jahr % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a "Datum" a equals b
	 * 
	 * @param a
	 *            takes (called on an instance of Datum e.q. b) a parameter of an
	 *            Object "Datum"
	 * @return returns true if it equals fully/ false if it doesn't
	 */
	public boolean equals(Datum a) {
		if (this.jahr == a.getJahr() && this.monat == a.getMonat() && this.tag == a.getTag()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the day is the same
	 * 
	 * @param a
	 *            takes (called of an instance of datum e.q b) a parameter object of
	 *            datum
	 * @return true if day and month are equal(the year doesnt matter)/false if not
	 */
	public boolean isGleicherTag(Datum a) {
		if (this.monat == a.getMonat() && this.tag == a.getTag()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns the String of day/month/year of the object
	 */
	public String toString() {
		return "" + this.tag + "/" + this.monat + "/" + this.jahr;
	}

	/**
	 * Constructor of Datum
	 * 
	 * @param tag
	 *            takes a day as parameter
	 * @param monat
	 *            takes the month as parameter
	 * @param jahr
	 *            takes the year as parameter
	 */
	public Datum(int tag, int monat, int jahr) {
		if (jahr > 1800 && jahr < 2100) {
			setJahr(jahr);
			if (monat >= 1 && monat <= 12) {
				setMonat(monat);
				if (monat == 2) {
					if (isSchaltjahr(jahr)) {
						if (tag >= 1 && tag <= 29) {
							setTag(tag);
						} else {
							throw new InvalidDateException("There is no day like the " + tag + " of Feburary!");
						}
					} else {
						if (tag >= 1 && tag <= monatslaengen[1]) {
							setTag(tag);
						} else {
							throw new InvalidDateException("your day is not a day of feburary");
						}
					}
				} else if (tag >= 1 && tag <= monatslaengen[monat - 1]) {
					setTag(tag);
				} else {
					throw new InvalidDateException("There is no day like the " + tag + " of " + monat);
				}
			} else {
				throw new InvalidDateException("There is no month like " + monat + ".");
			}
		} else {
			throw new DateOutOfRangeException(jahr + "is outside of 1800 and 2100");
		}
	}

	/**
	 * Constructor of Datum with only Days as param
	 * 
	 * @param tag
	 *            takes a day 1...366 as param.
	 * @param jahr
	 *            takes the year as param
	 */
	public Datum(int tag, int jahr) {
		if (jahr >= 1800 && jahr <= 2100) {
			int month = 0;
			setJahr(jahr);
			if (tag == 366) {
				for (int i = 0; tag >= 31; i++) {
					if (i == 1) {
						tag -= 29;
						month = 2;
					} else {
						tag -= monatslaengen[i];
						month = i + 1;
					}
				}
				setTag(tag);
				setMonat(month);
				setJahr(jahr);
			} else if (tag >= 1 && tag <= 365) {
				for (int i = 0; tag >= 31; i++) {
					tag -= monatslaengen[i];
					month = i + 1;
				}
				setTag(tag);
				setMonat(month);
				setJahr(jahr);
			} else {
				throw new InvalidDateException("The number " + tag + " is not inside one year");
			}
		} else {
			throw new DateOutOfRangeException("The year " + jahr + " is not inside 1800 and 2100");
		}
	}

	/**
	 * 
	 * @return which day is tomorrow according to the Object morgen is called on.
	 */
	public Datum morgen() {
		int d = this.tag;
		int m = this.monat;
		int y = this.jahr;
		if ((d + 1) >= monatslaengen[this.monat - 1]) {
			if (m == 12) {
				if (y == 2099) {
					throw new DateOutOfRangeException("The day after today is not in 2099 anymore");
				} else {
					y += 1;
					m = 1;
					d = 1;
				}
			} else {
				m += 1;
				d = 1;
			}
		} else {
			d += 1;
		}
		return new Datum(d, m, y);
	}

	/**
	 * 
	 * @return which day was yesterday according to the Object gestern is called on.
	 */
	public Datum gestern() {
		int d = this.tag;
		int m = this.monat;
		int y = this.jahr;
		if (d == 1) {
			if (m == 1) {
				if (y == 1801) {
					throw new DateOutOfRangeException("yesterday was before 1.1.1801");
				} else {
					y -= 1;
					m = 12;
					d = monatslaengen[11];
				}
			} else {
				m--;
				d = monatslaengen[m - 1];
			}
		} else {
			d--;
		}
		return new Datum(d, m, y);
	}

	/**
	 * setter
	 * 
	 * @param tag
	 */
	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * setter
	 * 
	 * @param monat
	 */
	public void setMonat(int monat) {
		this.monat = monat;
	}

	/**
	 * setter
	 * 
	 * @param jahr
	 */
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	/**
	 * getter
	 * 
	 * @return the day of the object
	 */
	public int getTag() {
		return this.tag;
	}

	/**
	 * getter
	 * 
	 * @return the month of the object
	 */
	public int getMonat() {
		return this.monat;
	}

	/**
	 * getter
	 * 
	 * @return the year of the object
	 */
	public int getJahr() {
		return this.jahr;
	}
}
