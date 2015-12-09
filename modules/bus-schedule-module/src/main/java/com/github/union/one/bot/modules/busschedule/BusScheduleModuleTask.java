package com.github.union.one.bot.modules.busschedule;

import java.io.IOException;

import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bot.api.ModuleTask;

public class BusScheduleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public BusScheduleModuleTask(Class<? extends ChatTask> chatTaskClass, String propFilePath) throws IOException {
		super(chatTaskClass, propFilePath);
	}

	/*
	 * You must override this method
	 */
	@Override
	public void configure(String propFilePath) throws IOException {
		// TODO Auto-generated method stub

	}
}
