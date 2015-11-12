package com.github.pixelase.bot.modules.example;

import com.github.pixelase.bot.api.UserTask;
import com.pengrad.telegrambot.model.User;

public class ExampleUserTask extends UserTask {

	public ExampleUserTask(User user) {
		super(user);
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (userTaskTimeout != 0) {
			sleep(userTaskDelay);
			bot.sendMessage(currentMessage.chat().id().intValue(), "Surprice motherfucker", null, null, null, null);
			System.out.printf("From %s task(%s): %s\n", user.username(), this.hashCode(), currentMessage.text());
		}
	}

}
