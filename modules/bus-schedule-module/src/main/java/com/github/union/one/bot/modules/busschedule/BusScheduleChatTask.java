package com.github.union.one.bot.modules.busschedule;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.github.pixelase.bot.api.ChatTask;
import com.github.union.one.bot.modules.busschedule.manager.Manager;
import com.github.union.one.bus.api.core.Schedule;
import com.github.union.one.bus.api.model.Code;
import com.github.union.one.bus.api.parser.CodeParser;
import com.github.union.one.bus.api.schedule.ScheduleImpl;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class BusScheduleChatTask extends ChatTask {

	private Schedule schedule;
	private String[][] state;
	private String[][] station;
	private ArrayList<Code> codes;
	private CodeParser parser;
	private String from;
	private String to;

	public BusScheduleChatTask(Chat chat) {
		super(chat);
		schedule = new ScheduleImpl();
		codes = new ArrayList<Code>();
		from = "";
		to = "";
		try {
			parser = new CodeParser();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser.parse(codes);

		state = new String[1][1];
		state[0] = new String[] { "Коммунист", "Капиталист" };
		station = Manager.convertToTwoDimension(codes);

	}

	@Override
	public void run() {
		bot.sendMessage(currentMessage.chat().id(), "Вас приветствует Председатель СНК СССР! Чего изволите, товарищ?");
		Keyboard keyboard = new ReplyKeyboardMarkup(state, true, false, false);
		Keyboard keyboard2 = new ReplyKeyboardMarkup(station, true, false, false);
		bot.sendMessage(currentMessage.chat().id(), "Выберите сторону", ParseMode.Markdown, null, null, keyboard);
		while (isRunning()) {
			sleep(chatTaskDelay);
			if (isMessageUpdated()) {
				try {
					if (currentMessage.text().startsWith("Коммунист")) {
						bot.sendMessage(currentMessage.chat().id(), "Выбирайте откуда начнется революция!!!",
								ParseMode.Markdown, null, null, keyboard2);
					}

					if (currentMessage.text().startsWith("/now")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("now", "s9757747", "c10274"));
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("now", "c10274", "s9757747"));
						bot.sendMessage(currentMessage.chat().id(), "Время не ждет, я понимаю!");
					} else if (currentMessage.text().startsWith("/today")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("today", "s9757747", "c10274"));
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("today", "c10274", "s9757747"));
						bot.sendMessage(currentMessage.chat().id(), "А вы свой человек, как я посмотрю, товарищ...");
					} else {
						for (Code code : codes) {
							if (currentMessage.text().startsWith(code.getName())) {
								if ("".equals(from)) {
									from = code.getCode();
									bot.sendMessage(currentMessage.chat().id(), "Кого захлеснет красная смерть?", ParseMode.Markdown, null, null,
											keyboard2);
								} else {
									to = code.getCode();
									bot.sendMessage(currentMessage.chat().id(),
											schedule.getSchedule("today", from, to));
									from = "";
									to = "";
									bot.sendMessage(currentMessage.chat().id(), "Выберите сторону", ParseMode.Markdown,
											null, null, keyboard);
								}
							}
						}
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
