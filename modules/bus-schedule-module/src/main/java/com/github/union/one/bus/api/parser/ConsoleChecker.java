package com.github.union.one.bus.api.parser;

import java.util.ArrayList;
import java.util.List;

import com.github.union.one.bus.api.model.Code;
import com.github.union.one.bus.api.schedule.ScheduleImpl;

public class ConsoleChecker {
	public static void main(String[] args) throws Exception {
		  //"s9757747", "c10274"
		  ScheduleImpl schedule = new ScheduleImpl();
		  System.out.println(schedule.getSchedule("now", "s9757747", "c10274"));
		  List<Code> codes = new ArrayList<>();
		  CodeParser parser = new CodeParser();
		  parser.parse(codes);
		  System.out.println(codes.toString());
	}
}