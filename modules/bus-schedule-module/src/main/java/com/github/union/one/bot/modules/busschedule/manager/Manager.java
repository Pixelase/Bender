package com.github.union.one.bot.modules.busschedule.manager;

import java.util.ArrayList;

import com.github.union.one.bus.api.model.Code;

public class Manager {
	public static String[][] convertToTwoDimension(ArrayList<Code> codes) {
		String array1d[] = convertToOneDimension(codes);
		String array2d[][] = new String[array1d.length/2][2];
		int k = 0;
		for(int i = 0; i < array1d.length / 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(k==array1d.length) {
					break;
				}
				array2d[i][j] = array1d[k];
				k++;
			}
		}
		return array2d;
	}

	private static String[] convertToOneDimension(ArrayList<Code> codes) {
		String array1d[] = new String[codes.size()];
		for (int i = 0; i < codes.size(); i++) {
			array1d[i] = codes.get(i).getName();
		}
		return array1d;
	}
}