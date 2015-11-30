package com.github.pixelase.bot.launcher;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServerTask;
import com.github.pixelase.bot.modules.busschedule.BusScheduleModuleTask;
import com.github.pixelase.bot.modules.busschedule.BusScheduleUserTask;
import com.github.pixelase.bot.modules.example.ExampleModuleTask;
import com.github.pixelase.bot.modules.example.ExampleUserTask;

public class ServerLauncher {
	public static void main(String[] args) throws IOException, InterruptedException {
		ModuleTask moduleTask = new ExampleModuleTask(ExampleUserTask.class, "test_module.properties");
		ModuleTask busScheduleTask = new BusScheduleModuleTask(BusScheduleUserTask.class, "test_module.properties");
		Server server = new BotServerTask("server.properties", busScheduleTask);
		server.start();
	}
}
