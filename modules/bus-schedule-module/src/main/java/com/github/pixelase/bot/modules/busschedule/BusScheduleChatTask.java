package com.github.pixelase.bot.modules.busschedule;

import java.net.MalformedURLException;
import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bus.api.parser.ConsoleChecker;
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
				try {
					bot.sendMessage(currentMessage.chat().id(), ConsoleChecker.result());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.printf("From %s task(%s): %s - %s\n", chat.username(), this.hashCode(), currentMessage.text(), currentMessage.messageId());
			}
		}
	}

}
