package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;

public abstract class Task implements Runnable {

	protected static TelegramBot bot;
	protected static Message currentMessage;
	protected static boolean isOk;
	protected static long taskDelay;

	static {
		currentMessage = null;
		isOk = true;
	}

	public static long getTaskDelay() {
		return taskDelay;
	}

	public static void setTaskDelay(long taskDelay) {
		Task.taskDelay = taskDelay;
	}

	protected void sleep(long millis) {
		/*
		 * Hot fix
		 */
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
