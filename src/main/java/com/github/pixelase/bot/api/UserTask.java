package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.User;

public abstract class UserTask implements Runnable {
	private final User user;

	public UserTask(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
