package de.cpelzer.aufgaben.zahlenfeld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataList {
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public DataList(ArrayList<Integer> list) {
		
	}
	
	@SuppressWarnings("unused")
	public DataList(String filename) {
		String s = "";
		String zeile;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while((zeile = br.readLine()) == null) {
				
				char[] arr = zeile.toCharArray();
				
				if(arr.length == 0) {
					continue;
				} else if(arr[0] == '%') {
					continue;
				} 
				else {
					for(char x : arr) {
						if(x != ' ') {
							s += x;
						}
					}
				}
				int i = Integer.valueOf(s);
				list.add(i);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
