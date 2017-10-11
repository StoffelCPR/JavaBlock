package de.cpelzer.aufgaben.datum;

public class Datum {

	public static final int[] monatslaengen = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static final String[] wochentage = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };

	private int tag, monat, jahr;

	public static int getMonatslaenge(int monat, int jahr) {
		if (monat == 2) {
			if (isSchaltjahr(jahr)) {
				return 29;
			}
		}
		return monatslaengen[monat - 1];
	}

	public static boolean isSchaltjahr(int jahr) {
		if (jahr % 4 == 0 && jahr % 100 != 0 || jahr % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Datum a) {
		if (this.jahr == a.getJahr() && this.monat == a.getMonat() && this.tag == a.getTag()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isGleicherTag(Datum a) {
		if (this.monat == a.getMonat() && this.tag == a.getTag()) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "" + this.tag + "/" + this.monat + "/" + this.jahr;
	}

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
						if(tag >= 1 && tag <= monatslaengen[1]) {
							setTag(tag);
						} else {
							throw new InvalidDateException("your day is not a day of feburary");
						}
					}
				}
				else if (tag >= 1 && tag <= monatslaengen[monat - 1] ){
					setTag(tag);
				} else {
					throw new InvalidDateException("There is no day like the " + tag + " of " + monat);
					} 
				}
				else {
				throw new InvalidDateException("There is no month like " + monat + ".");
				}
		} else {
					throw new DateOutOfRangeException(jahr + "is outside of 1800 and 2100");
				}
			}

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

	public Datum morgen() {
		int d = this.tag;
		int m = this.monat;
		int y = this.jahr;
		if ((d+1) >= monatslaengen[this.monat-1]) {
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

	public void setTag(int tag) {
		this.tag = tag;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public int getTag() {
		return this.tag;
	}

	public int getMonat() {
		return this.monat;
	}

	public int getJahr() {
		return this.jahr;
	}
}
