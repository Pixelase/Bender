package com.github.pixelase.bot.modules.busschedule;

import java.util.Arrays;

import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.pengrad.telegrambot.model.Chat;

public class BusScheduleChatTask extends ChatTask {

	public BusScheduleChatTask(Chat chat) {
		super(chat);
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (isRunning()) {
			sleep(chatTaskDelay);
			if (isMessageUpdated()) {
				bot.sendMessage(currentMessage.chat().id(), Arrays.toString(Emoji.values()), null, null, null, null);
				System.out.printf("From %s task(%s): %s - %s\n", chat.username(), this.hashCode(), currentMessage.text(), currentMessage.messageId());
			}
		}
	}

}
