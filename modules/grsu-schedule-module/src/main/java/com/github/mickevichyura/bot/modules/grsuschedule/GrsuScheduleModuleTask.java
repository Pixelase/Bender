package com.github.mickevichyura.bot.modules.grsuschedule;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.UserTask;

public class GrsuScheduleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public GrsuScheduleModuleTask(Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
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
