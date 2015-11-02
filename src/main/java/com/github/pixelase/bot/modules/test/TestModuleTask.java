package com.github.pixelase.bot.modules.test;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.UserTask;

public class TestModuleTask extends ModuleTask {

	/*
	 * You must implements this constructor
	 */
	public TestModuleTask(Class<? extends UserTask> userTaskClass) throws IOException {
		super(userTaskClass);
	}

	@Override
	public void configure() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
