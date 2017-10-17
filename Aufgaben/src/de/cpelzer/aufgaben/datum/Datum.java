package de.cpelzer.aufgaben.datum;

import java.util.Arrays;

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

	public static final int[] monatslaengenLeap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * the name of the days of a week
	 */
	public static final String[] wochentage = { "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" };

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
		if (isSchaltjahr(jahr)) {
			return monatslaengenLeap[monat - 1];
		} else {
			return monatslaengen[monat - 1];
		}
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
		if (this.getJahr() == a.getJahr() && this.getMonat() == a.getMonat() && this.getTag() == a.getTag()) {
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
		if (this.getMonat() == a.getMonat() && this.getTag() == a.getTag()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns the String of day/month/year of the object
	 */
	public String toString() {
		return "" + this.getTag() + "/" + this.getMonat() + "/" + this.getJahr();
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

		setJahr(jahr);

		setMonat(monat);

		setTag(tag);
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
		if (tag <= 1 || tag >= 366) {
			throw new InvalidDateException("The number " + tag + " is more or less then one year!");
		} else {
			setJahr(jahr);
			if (isSchaltjahr(jahr)) {
				int i = 0;
				while (tag > monatslaengenLeap[i]) {
					tag -= monatslaengenLeap[i];
					i++;
					setMonat(i + 1);
				}
				setTag(tag);
			} else {
				int i = 0;
				while (tag > monatslaengen[i]) {
					tag -= monatslaengen[i];
					i++;
					setMonat(i + 1);
				}
				setTag(tag);
			}

		}
	}

	/**
	 * 
	 * @return which day is tomorrow according to the Object morgen is called on.
	 */
	public Datum morgen() {
		int d = this.getTag();
		int m = this.getMonat();
		int y = this.getJahr();
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
				if (isSchaltjahr(this.getJahr())) {
					int[] monatLaenge = Arrays.copyOf(monatslaengen, 12);
					monatLaenge[1] = 29;
					d = monatLaenge[m - 1];
				} else {
					d = monatslaengen[m - 1];
				}
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
		if (isSchaltjahr(this.getJahr())) {
			if (tag >= 1 && tag <= monatslaengenLeap[this.getMonat() - 1]) {
				this.tag = tag;
			} else {
				throw new InvalidDateException("There is no day like the " + tag + " of " + monat);
			}
		} else {
			if (tag >= 1 && tag <= monatslaengen[this.getMonat() - 1]) {
				this.tag = tag;
			} else {
				throw new InvalidDateException("There is no day like the " + tag + " of " + monat);
			}
		}
	}

	/**
	 * setter
	 * 
	 * @param monat
	 */
	public void setMonat(int monat) {
		if (monat >= 1 && monat <= 12) {
			this.monat = monat;
		} else {
			throw new InvalidDateException("There is no month like " + monat + ".");
		}
	}

	/**
	 * setter
	 * 
	 * @param jahr
	 */
	public void setJahr(int jahr) {
		if (jahr > 1800 && jahr < 2100) {
			this.jahr = jahr;
		} else {
			throw new DateOutOfRangeException(jahr + "is outside of 1800 and 2100");
		}
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

	public String getWochentag() {
		int day = this.getTag();
		int jahrHunderDoomsday = 2 - ((this.getJahr() / 100) % 4) * 2;
		int jahrEndZahl = this.getJahr() % 100;
		int doomsday = (jahrEndZahl + (jahrEndZahl / 4) + jahrHunderDoomsday) % 7;

		if (isSchaltjahr(this.getJahr())) {
			for (int i = 0; i < this.getMonat() - 1; i++) {
				day += monatslaengenLeap[i];
			}
			if (day >= 60) {
				day -= 60;
				day %= 7;
				day += doomsday;

			} else {
				while (day < 60) {
					day += 7;
				}
				day -= 60;
				day += doomsday;
				day %= 7;

			}
		} else {
			for (int i = 0; i < this.getMonat() - 1; i++) {
				day += monatslaengen[i];
			}
			if (day >= 59) {
				day -= 59;
				day %= 7;
				day += doomsday;
				if (day >= 7) {
					day %= 7;
				}
				System.out.println(day);
			} else {
				while (day < 59) {
					day += 7;
				}
				day -= 59;
				day += doomsday;
				day %= 7;
			}
		}
		return wochentage[day];
	}
}
