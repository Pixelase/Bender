package com.github.union.one.bus.api.parser;

import java.util.ArrayList;

import com.github.union.one.bot.modules.busschedule.manager.Manager;
import com.github.union.one.bus.api.model.Code;

public class ConsoleChecker {
	public static void main(String[] args) throws Exception {
		  //"s9757747", "c10274"
		  //ScheduleImpl schedule = new ScheduleImpl();
		  //System.out.println(schedule.getSchedule("now", "s9757747", "c10274"));
		ArrayList<Code> codes = new ArrayList<>();
		CodeParser parser = new CodeParser();
		parser.parse(codes);
		
		
		String[][] array2d = Manager.convertToTwoDimension(codes);
		for(int i = 0; i < codes.size() / 2; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(array2d[i][j] + " ");
			}
			System.out.println();
		}
	}
}