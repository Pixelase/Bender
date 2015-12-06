package com.github.mickevichyura.bot.modules.grsuschedule.persons;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.mickevichyura.grsu.api.response.DayResponse;
import com.github.mickevichyura.grsu.api.response.GetModels;
import com.github.mickevichyura.grsu.api.response.TeacherResponse;
import com.github.mickevichyura.grsu.api.utils.Api;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Teacher implements Person {

	private boolean isConfig;

	private TeacherResponse teacherResponse;
	private String teacherId;

	public Teacher() {
		super();
	}

	@Override
	public boolean isConfig() {
		return isConfig;
	}

	@Override
	public void config(TelegramBot bot, Message currentMessage) {
		if (teacherResponse == null) {
			String url = Api.LECTURER_LIST;
			teacherResponse = GetModels.getModels(url, TeacherResponse.class);
			Collections.sort(teacherResponse.getItems());
		}

		String sendMessage = "";
		Keyboard rkm = null;

		String name = currentMessage.text();
		if (currentMessage.text().startsWith("/teacher")) {
			isConfig = false;
			int indexOfSpace = currentMessage.text().indexOf(" ") + 1;
			name = indexOfSpace != 0 ? currentMessage.text().substring(indexOfSpace) : "";
		}
		
		if (!isConfig) {
			teacherId = teacherResponse.findId(name);
			if (teacherId != null) {
				rkm = new ForceReply();
				sendMessage = currentMessage.text();
				isConfig = true;
			}
		}

		if (teacherId == null) {
			String[][] array = teacherResponse.itemsToStringArray(teacherResponse.contains(name), 3);
			sendMessage = "Find: ";
			rkm = new ReplyKeyboardMarkup(array, true, false, false);
		}

		bot.sendMessage(currentMessage.chat().id(), sendMessage, ParseMode.Markdown, null, null, rkm);
		if (isConfig) {
			sendSchedule(bot, currentMessage, 0);
		}
	}

	@Override
	public void sendSchedule(TelegramBot bot, Message currentMessage, int day) {
		TimeUnit t = TimeUnit.MILLISECONDS;
		long daySeconds = t.convert(1L, TimeUnit.DAYS);

		Date date = new Date(t.convert(currentMessage.date(), TimeUnit.SECONDS) + daySeconds * day);
		DayResponse teacherSchedule = GetModels.getModels(Api.TEACHER_SCHEDULE + teacherId, DayResponse.class);

		String markdown = teacherSchedule.getDays().get(0).toString();
		bot.sendMessage(currentMessage.chat().id(), markdown, ParseMode.Markdown, null, null, null);

	}

}
