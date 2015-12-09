package com.github.union.one.bot.modules.busschedule;

import com.github.pixelase.bot.api.ChatTask;
import com.github.union.one.bus.api.parser.Schedule;
import com.pengrad.telegrambot.model.Chat;

public class BusScheduleChatTask extends ChatTask {

	public Schedule schedule = new Schedule();

	public BusScheduleChatTask(Chat chat) {
		super(chat);
	}

	@Override
	public void run() {
		while (isRunning()) {
			sleep(chatTaskDelay);
			if (isMessageUpdated()) {
				try {
					if (currentMessage.text().startsWith("/today")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.printf("From %s task(%s): %s - %s\n", chat.username(), this.hashCode(),
						currentMessage.text(), currentMessage.messageId());
			}
		}
	}
}