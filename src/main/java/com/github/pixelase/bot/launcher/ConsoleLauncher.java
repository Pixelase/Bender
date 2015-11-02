package com.github.pixelase.bot.launcher;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServer;
import com.github.pixelase.bot.modules.test.TestModuleTask;
import com.github.pixelase.bot.modules.test.TestUserTask;

public class ConsoleLauncher {
	public static void main(String[] args) throws IOException, InterruptedException {
		ModuleTask weatherModule = new TestModuleTask(TestUserTask.class,
				".\\src\\main\\resources\\test_module.properties");
		Server server = new BotServer(".\\src\\main\\resources\\server.properties", weatherModule);
		server.start();
	}
}
