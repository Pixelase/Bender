package com.github.mickevichyura.bot.modules.grsuschedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.mickevichyura.grsu.api.response.BaseResponse;
import com.github.mickevichyura.grsu.api.response.DayResponse;
import com.github.mickevichyura.grsu.api.response.GetModels;
import com.github.mickevichyura.grsu.api.response.TeacherResponse;
import com.github.mickevichyura.grsu.api.utils.Api;
import com.github.pixelase.bot.api.UserTask;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class GrsuScheduleUserTask extends UserTask {

	private boolean isConfig;
	private List<String> settings; // department, faculty, course, group

	private boolean isTeacherConfig;
	private String teacherId;

	private final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	private BaseResponse baseResponse;
	private TeacherResponse teacherResponse;

	private Message temp;

	public GrsuScheduleUserTask(User user) {
		super(user);
		settings = new ArrayList<String>();
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (isRunning()) {
			sleep(userTaskDelay);

			// !currentMessage.equals(temp)

			if (!currentMessage.equals(temp)) {
				temp = currentMessage;

				if (currentMessage.text().startsWith("/teacher") || !isTeacherConfig) {
					String name = currentMessage.text();
					if (currentMessage.text().startsWith("/teacher")) {
						isTeacherConfig = false;
						int indexOfSpace = currentMessage.text().indexOf(" ") + 1;
						name = indexOfSpace != 0 ? currentMessage.text().substring(indexOfSpace) : "";
					}
					teacherConfig(name);
				}

				else if (!isConfig || currentMessage.text().startsWith("/settings")) {
					config();
				} else {
					int day = 0;
					if (currentMessage.text().startsWith("/tomorrow")) {
						day = 1;
					}
					if (currentMessage.text().startsWith("/yesterday")) {
						day = -1;
					}
					sendSchedule(day);
				}

				System.out.printf("From %s task(%s): %s\n", user.username(), this.hashCode(), currentMessage.text());
			}
		}
	}

	private void teacherConfig(String name) {

		if (teacherResponse == null) {
			String url = Api.LECTURER_LIST;
			teacherResponse = GetModels.getModels(url, TeacherResponse.class);
			Collections.sort(teacherResponse.getItems());
		}

		String sendMessage = "";
		Keyboard rkm = null;

		if (!isTeacherConfig) {
			teacherId = teacherResponse.findId(name);
			if (teacherId != null) {
				sendMessage = teacherId + " " + name;
				rkm = new ForceReply();
				isTeacherConfig = true;
			}
		}

		if (teacherId == null) {
			String[][] array = teacherResponse.itemsToStringArray(teacherResponse.contains(name), 3);
			sendMessage = "Find: ";
			rkm = new ReplyKeyboardMarkup(array, true, false, false);
		}

		bot.sendMessage(currentMessage.chat().id(), sendMessage, ParseMode.Markdown, null, null, rkm);

	}

	private void config() {
		String url = null;
		String sendMessage = "";
		if (currentMessage.text().startsWith("/settings")) {
			isConfig = false;
			settings.clear();

		}
		if (baseResponse != null) {
			String id = baseResponse.findId(currentMessage.text());
			if (id != null) {
				settings.add(id);
			}
		}

		switch (settings.size()) {
		case 1:
			url = Api.FACULTY_LIST;
			sendMessage = "Выберите факультет";
			break;

		case 2:
			url = Api.COURSE_LIST;
			sendMessage = "Выберите курс";
			break;

		case 3:
			url = Api.groupList(settings.get(0), settings.get(1), settings.get(2));
			sendMessage = "Выберите группу";
			break;

		case 4:
			isConfig = true;
			break;

		default:
			url = Api.DEPARTMENT_LIST;
			System.out.println(settings.size() + "start");
			sendMessage = "Выберите форму обучения";
			break;
		}

		Keyboard rkm = null;

		if (!isConfig) {
			baseResponse = GetModels.getModels(url);
			String[][] array = baseResponse.itemsToStringArray(2);
			rkm = new ReplyKeyboardMarkup(array, true, false, false);
		} else {
			rkm = new ForceReply();
			sendMessage = settings.toString();
		}

		bot.sendMessage(currentMessage.chat().id(), sendMessage, ParseMode.Markdown, null, null, rkm);
		if (isConfig) {
			sendSchedule(0);
		}
	}

	private void sendSchedule(int day) {
		TimeUnit t = TimeUnit.MILLISECONDS;
		long daySeconds = t.convert(1L, TimeUnit.DAYS);

		Date date = new Date(t.convert(currentMessage.date(), TimeUnit.SECONDS) + daySeconds * day);
		DayResponse groupSchedule = GetModels.getModels(Api.groupSchedule(settings.get(3)) + DATE_FORMAT.format(date),
				DayResponse.class);
		
		String markdown = groupSchedule.getDays().get(0).toString();
		bot.sendMessage(currentMessage.chat().id(), markdown, ParseMode.Markdown, null, null, null);

	}

}
