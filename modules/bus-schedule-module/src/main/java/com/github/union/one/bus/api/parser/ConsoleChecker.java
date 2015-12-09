package com.github.union.one.bus.api.parser;

import com.github.union.one.bus.api.schedule.ScheduleImpl;

public class ConsoleChecker {
	public static void main(String[] args) throws Exception {
		  ScheduleImpl schedule = new ScheduleImpl();
		  System.out.println(schedule.getSchedule("today"));
	}
}