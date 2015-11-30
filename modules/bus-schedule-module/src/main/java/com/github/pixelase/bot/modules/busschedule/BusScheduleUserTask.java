package com.github.pixelase.bot.modules.busschedule;

import java.util.Arrays;
import java.util.Random;

import com.github.pixelase.bot.api.UserTask;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.pengrad.telegrambot.model.User;

public class BusScheduleUserTask extends UserTask {

	public BusScheduleUserTask(User user) {
		super(user);
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (isRunning()) {
			sleep(userTaskDelay);
			if (isMessageUpdated()) {
				bot.sendMessage(currentMessage.chat().id(), Arrays.toString(Emoji.values()), null, null, null, null);
				System.out.printf("From %s task(%s): %s - %s\n", user.username(), this.hashCode(), currentMessage.text(), currentMessage.messageId());
			}
		}
	}

}
