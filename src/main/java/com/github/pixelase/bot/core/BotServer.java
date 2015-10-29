package com.github.pixelase.bot.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.pixelase.bot.api.Module;
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
	private Module[] modules;

	private BotServer() {
		properties = new Properties();
		moduleExecutor = Executors.newCachedThreadPool();
		isStarted = false;
	}

	public BotServer(String propFilePath, Module... modules) throws IOException {
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
		GetUpdatesResponse getUpdatesResponse = bot.getUpdates(0, 100, 0);
		Update currentUpdate = getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1);

		while (getUpdatesResponse.isOk()) {
			getUpdatesResponse = bot.getUpdates(0, 100, 0);

			if (currentUpdate.updateId()
					.equals(getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1).updateId())) {
				continue;
			}

			currentUpdate = getUpdatesResponse.updates().get(getUpdatesResponse.updates().size() - 1);

			/*
			 * Update message for each module
			 */
			for (Module module : modules) {
				module.updateMessage(currentUpdate.message());
				module.updateState(getUpdatesResponse.isOk());
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
			for (Module module : modules) {
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
