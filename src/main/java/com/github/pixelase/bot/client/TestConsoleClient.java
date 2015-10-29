package com.github.pixelase.bot.client;

import java.io.IOException;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServer;
import com.github.pixelase.bot.modules.weather.WeatherModuleTask;

public class TestConsoleClient {
	public static void main(String[] args) throws IOException {
		ModuleTask weatherModule = new WeatherModuleTask();
		Server server = new BotServer("local.properties", weatherModule);
		server.start();
	}
}
