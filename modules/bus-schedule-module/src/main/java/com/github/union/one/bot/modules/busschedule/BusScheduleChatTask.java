package com.github.union.one.bot.modules.busschedule;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.github.pixelase.bot.api.ChatTask;
import com.github.union.one.bot.modules.busschedule.manager.Answer;
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
	private String type;

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
		state[0] = new String[] { Answer.NOW, Answer.TODAY };
		station = Manager.convertToTwoDimension(codes);
		type = "";

	}

	@Override
	public void run() {
		bot.sendMessage(currentMessage.chat().id(), Answer.WELCOME);
		Keyboard stateKeyboard = new ReplyKeyboardMarkup(state, true, false, false);
		Keyboard stationKeyboard = new ReplyKeyboardMarkup(station, true, false, false);
		bot.sendMessage(currentMessage.chat().id(), Answer.TYPE_SCHEDULE, ParseMode.Markdown, null, null, stateKeyboard);
		while (isRunning()) {
			sleep(chatTaskDelay);
			if (isMessageUpdated()) {
				try {
					if (currentMessage.text().startsWith(Answer.NOW) || currentMessage.text().startsWith(Answer.TODAY)) {
						if (currentMessage.text().startsWith(Answer.NOW)) {
							type = "now";
						} else if (currentMessage.text().startsWith(Answer.TODAY)) {
							type = "today";
						}
						bot.sendMessage(currentMessage.chat().id(), Answer.FROM,
								ParseMode.Markdown, null, null, stationKeyboard);
					} 
					if (currentMessage.text().startsWith("/now")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("now", "s9757747", "c10274"));
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("now", "c10274", "s9757747"));
					} else if (currentMessage.text().startsWith("/today")) {
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("today", "s9757747", "c10274"));
						bot.sendMessage(currentMessage.chat().id(), schedule.getSchedule("today", "c10274", "s9757747"));
					} else {
						for (Code code : codes) {
							if (currentMessage.text().startsWith(code.getName())) {
								if ("".equals(from)) {
									from = code.getCode();
									bot.sendMessage(currentMessage.chat().id(), Answer.TO, ParseMode.Markdown, null, null,
											stationKeyboard);
								} else {
									to = code.getCode();
									bot.sendMessage(currentMessage.chat().id(),
											schedule.getSchedule(type, from, to));
									from = "";
									to = "";
									bot.sendMessage(currentMessage.chat().id(), Answer.TYPE_SCHEDULE, ParseMode.Markdown,
											null, null, stateKeyboard);
								}
							}
						}
					} 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					bot.sendMessage(currentMessage.chat().id(), Answer.SORRY);
					bot.sendMessage(currentMessage.chat().id(), Answer.TO);
				}
				System.out.printf("From %s task(%s): %s - %s\n", chat.username(), this.hashCode(),
						currentMessage.text(), currentMessage.messageId());
			}
		}
	}
}
