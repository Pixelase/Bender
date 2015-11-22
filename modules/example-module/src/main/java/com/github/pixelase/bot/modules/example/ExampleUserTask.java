package com.github.pixelase.bot.modules.example;

import java.util.Random;

import com.github.pixelase.bot.api.UserTask;
import com.github.pixelase.bot.utils.emoji.Emoji;
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
		while (isRunning()) {
			sleep(userTaskDelay);
			Random random = new Random();
			if (isMessageUpdated()) {
				bot.sendMessage(currentMessage.chat().id().intValue(),
						Emoji.values()[random.nextInt(Emoji.values().length - 1)].toString(), null, null, null, null);
				System.out.printf("From %s task(%s): %s - %s\n", user.username(), this.hashCode(), currentMessage.text(), currentMessage.messageId());
			}
		}
	}

}
