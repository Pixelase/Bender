package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.Message;

public abstract class Task implements Runnable {
	protected Message message;
	protected boolean isOk;
	protected static long taskDelay;

	public Task() {
		this(null, true);
	}

	private Task(Message message, boolean isOk) {
		super();
		this.message = message;
		this.isOk = isOk;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
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
