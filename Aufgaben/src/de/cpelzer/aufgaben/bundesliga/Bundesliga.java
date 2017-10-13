package de.cpelzer.aufgaben.bundesliga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.omg.CORBA.portable.ValueOutputStream;

public class Bundesliga {
	private String[] mannschaften = new String[18];
	private int[][] toreHeim = new int[18][2];
	private int[][] toreGast;
	
	public Bundesliga(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String zeile = null;
		char[] temp;
		int j = 0;
		
		while((zeile = br.readLine()) != null) {
			temp = zeile.toCharArray();
			String manTemp = "";
			String[] weird;
			String[] ergebnis;
			String fine = "";
			String tempo = "";

			for (int i = 0; i < 25; i++) {
				manTemp += temp[i];
			}
			
			weird = manTemp.split(" ");
			for(int k = 0; k < weird.length; k++) {
				//System.out.printf(weird[k]);
				tempo += weird[k] + " ";
			}
			mannschaften[j] = tempo;

			
			ergebnis = zeile.split(manTemp);
			for(int i = 0; i < ergebnis.length; i++) {
				ergebnis[i].replaceAll("---", "-1");
				fine += ergebnis[i];
			}
			
			
			String[] elf = fine.split(":");
			for(int i = 0; i < toreHeim.length; i++) {
				for(int k = 0; k < toreHeim.length; k++) {
					if(ergebnis[k] == String.valueOf(-1)) {
						toreHeim[i][k] = -1;
						continue;
					} else {
						toreHeim[i][k] = Integer.valueOf(elf[i]);
					}
				}
			}
			
			j++;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		}
		for (int i = 0; i < 18; i++) {
			System.out.println(mannschaften[i]);
		}
		br.close();
	}

	
}
