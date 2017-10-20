package de.cpelzer.aufgaben.bundesliga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Bundesliga {
	
	private String[] mannschaften = new String[18];
	
	private int[][][] heimspiele = new int[18][18][2];
	private int[][] auswaertsspiele = new int[18][2];
	

	
	public Bundesliga (String filename) throws FileNotFoundException {
		String zeile;
		String temp;
		String fin;
		String[] temper;
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {		
			int i = 0;
			while((zeile = br.readLine()) != null) {
				
					temp = "";
					fin = "";
					temp = zeile.substring(0, 25);
					for(String x : temp.split(" ")) {
						fin += " " +  x;
					}
					mannschaften[i] = fin;
					
					
					
					temp = zeile.substring(25, zeile.length());
					temper = temp.split(" ");
					for(int j = 0; j < 18; j++) {
						for(int k = 0; k < 2; k++) {
							
							if(temper[j].equals("---")) {
								heimspiele[i][j][k] = -1;
								continue;
							}
							else if(k == 0) {
								heimspiele[i][j][k] = temper[j].charAt(0)-48;
							} else {
								heimspiele[i][j][k] = temper[j].charAt(2)-48;
							}
						}
					}

					i++;
			}
			for(int f = 0; f < heimspiele.length; f++) {
				for(int j = 0; j < heimspiele[f].length; j++) {
					for(int k = 0; k < heimspiele[f][j].length; k++) {
						System.out.printf("" + heimspiele[f][j][k]);
						if(k == 0) {
							System.out.printf(":");
						}
					}
					System.out.printf("  ");
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
