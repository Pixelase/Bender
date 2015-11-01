package com.github.pixelase.bot.modules.weather;

import java.lang.reflect.InvocationTargetException;

import com.github.pixelase.bot.api.ModuleTask;

public class WeatherModuleTask extends ModuleTask {

	public WeatherModuleTask() {
		super();
	}

	@Override
	public void run() {
		try {
			startExecution(WeatherUserTask.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
