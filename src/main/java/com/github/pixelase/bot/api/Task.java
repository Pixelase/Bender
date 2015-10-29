package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.Message;

public abstract class Task implements Runnable {
	protected Message message;
	protected boolean isOk;

	public Task() {
		this(null, true);
	}

	public Task(Message message, boolean isOk) {
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
}
