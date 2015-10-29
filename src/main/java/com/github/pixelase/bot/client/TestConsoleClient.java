package com.github.pixelase.bot.client;

import java.io.IOException;

import com.github.pixelase.bot.api.Module;
import com.github.pixelase.bot.api.Server;
import com.github.pixelase.bot.core.BotServer;
import com.github.pixelase.bot.modules.WeatherModule;

public class TestConsoleClient {
	public static void main(String[] args) throws IOException {
		Module weatherModule = new WeatherModule();
		Server server = new BotServer("local.properties", weatherModule);
		server.start();
	}
}
