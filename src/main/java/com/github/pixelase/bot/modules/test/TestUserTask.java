package com.github.pixelase.bot.modules.test;

import com.github.pixelase.bot.api.UserTask;
import com.pengrad.telegrambot.model.User;

public class TestUserTask extends UserTask {

	public TestUserTask(User user) {
		super(user);
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (userTaskTimeout != 0) {
			sleep(userTaskDelay);
			System.out.printf("From %s task(%s): %s\n", user.username(), this.hashCode(), message.text());
		}
	}

}
