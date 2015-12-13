package com.github.union.one.bot.modules.busschedule;

import com.github.pixelase.bot.api.ChatTask;
import com.github.union.one.bus.api.core.Schedule;
import com.github.union.one.bus.api.schedule.ScheduleImpl;
import com.pengrad.telegrambot.model.Chat;

public class BusScheduleChatTask extends ChatTask {

	Schedule schedule = new ScheduleImpl();

	public BusScheduleChatTask(Chat chat) {
		super(chat);
	}

	@Override
	public void run() {
		while (isRunning()) {
			sleep(chatTaskDelay);
			if (isMessageUpdated()) {
				try {
					if (currentMessage.text().startsWith("/now")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("now"));
					} else if (currentMessage.text().startsWith("/today")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("today"));
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