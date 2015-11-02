package com.github.pixelase.bot.launcher;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServer;
import com.github.pixelase.bot.modules.test.TestModuleTask;
import com.github.pixelase.bot.modules.test.TestUserTask;

public class ConsoleLauncher {
	public static void main(String[] args) throws IOException {
		ModuleTask weatherModule = new TestModuleTask(TestUserTask.class);
		Server server = new BotServer(".\\src\\main\\resources\\local.properties", weatherModule);
		server.start();
	}
}
