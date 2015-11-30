package com.github.pixelase.bot.modules.busschedule;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.UserTask;

public class BusScheduleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public BusScheduleModuleTask(Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
		super(userTaskClass, propFilePath);
	}

	/*
	 * You must override this method
	 */
	@Override
	public void configure(String propFilePath) throws IOException {
		// TODO Auto-generated method stub

	}
}
