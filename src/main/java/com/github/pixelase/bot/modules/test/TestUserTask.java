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
		 * TODO UserTask.getDelay() should be used here. Exactly not here, but in
		 * the abstract class UserTask;
		 */
		System.out.printf("From %s task(%s): %s\n", user.username(), this.hashCode(), message.text());
	}

}
