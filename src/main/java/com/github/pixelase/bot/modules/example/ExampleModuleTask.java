package com.github.pixelase.bot.modules.example;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.UserTask;

public class ExampleModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public ExampleModuleTask(Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
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
