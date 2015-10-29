package com.github.pixelase.bot.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.Server;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

public class BotServer implements Server {
	private TelegramBot bot;
	private Properties properties;
	private ExecutorService moduleExecutor;
	private boolean isStarted;
	private ModuleTask[] modules;

	private BotServer() {
		properties = new Properties();
		moduleExecutor = Executors.newCachedThreadPool();
		isStarted = false;
	}

	public BotServer(String propFilePath, ModuleTask... modules) throws IOException {
		this();
		this.modules = modules;

		File propFile = new File(propFilePath);

		if (propFile.exists()) {
			properties.load(new FileInputStream(propFile));
			bot = TelegramBotAdapter.build(properties.getProperty("token"));
		} else {
			throw new FileNotFoundException("The properties file is not found");
		}
	}

	private void fetchUpdates() {
		int offset = 0;
		int limit = 2;
		GetUpdatesResponse getUpdatesResponse = bot.getUpdates(offset, limit, 0);
		Update currentUpdate = getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1);

		while (getUpdatesResponse.isOk()) {
			offset = (getUpdatesResponse.updates().size() == limit) ? currentUpdate.updateId() : 0;
			getUpdatesResponse = bot.getUpdates(offset, limit, 0);

			if (currentUpdate.updateId()
					.equals(getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1).updateId())) {
				continue;
			}

			currentUpdate = getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1);

			/*
			 * Update message for each module
			 */
			for (ModuleTask module : modules) {
				module.setMessage(currentUpdate.message());
				module.setOk(getUpdatesResponse.isOk());
			}

			/*
			 * For debug
			 */
			System.out
					.println("Message from " + this.getClass().getSimpleName() + " " + currentUpdate.message().text());
		}
	}

	@Override
	public void start() {
		if (isStarted) {
			System.out.println("The server is already started");
		}

		if (modules.length != 0) {
			for (ModuleTask module : modules) {
				moduleExecutor.submit(module);
			}
		}
		isStarted = true;
		fetchUpdates();
	}

	@Override
	public void stop() {
		if (!isStarted) {
			System.out.println("The server hasn't been started");
		}
		moduleExecutor.shutdown();
		isStarted = false;
	}

	@Override
	public void refresh() {
		isStarted = false;
		start();
	}
}
