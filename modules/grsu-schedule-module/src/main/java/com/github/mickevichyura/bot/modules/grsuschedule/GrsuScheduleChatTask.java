package com.github.mickevichyura.bot.modules.grsuschedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.mickevichyura.grsu.api.response.BaseResponse;
import com.github.mickevichyura.grsu.api.response.DayResponse;
import com.github.mickevichyura.grsu.api.response.GetModels;
import com.github.mickevichyura.grsu.api.response.TeacherResponse;
import com.github.mickevichyura.grsu.api.utils.Api;
import com.github.pixelase.bot.api.ChatTask;
import com.github.pixelase.bot.utils.emoji.Emoji;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardHide;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class GrsuScheduleChatTask extends ChatTask {

	private boolean isConfig;
	private List<String> settings; // department, faculty, course, group

	String[][] persons;

	private boolean isTeacherConfig;
	private String teacherId;

	private final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	private BaseResponse baseResponse;
	private TeacherResponse teacherResponse;

	public GrsuScheduleChatTask(Chat chat) {
		super(chat);
		settings = new ArrayList<String>();
		persons = new String[1][2];
		persons[0] = new String[] { "Студент", "Преподаватель" };
	}

	@Override
	public void run() {
		/*
		 * Example of UserTask implementation
		 */
		while (isRunning()) {
			sleep(chatTaskDelay);

			if (isMessageUpdated()) {

//				if (currentMessage.text().startsWith("/teacher") || !isTeacherConfig) {
//					String name = currentMessage.text();
//					if (currentMessage.text().startsWith("/teacher")) {
//						isTeacherConfig = false;
//						int indexOfSpace = currentMessage.text().indexOf(" ") + 1;
//						name = indexOfSpace != 0 ? currentMessage.text().substring(indexOfSpace) : "";
//					}
//					teacherConfig(name);
//				}
//
//				else
					if (!isConfig || currentMessage.text().startsWith("/settings")) {
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

				System.out.printf("From %s task(%s): %s\n", chat.title(), this.hashCode(), currentMessage.text());
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
				rkm = new ForceReply();
				sendMessage = currentMessage.text();
				isTeacherConfig = true;
			}
		}

		if (teacherId == null) {
			String[][] array = teacherResponse.itemsToStringArray(teacherResponse.contains(name), 3);
			sendMessage = "Find: ";
			rkm = new ReplyKeyboardMarkup(array, true, false, false);
		}

		bot.sendMessage(currentMessage.chat().id(), sendMessage, ParseMode.Markdown, null, null, rkm);
		sendTeacherSchedule(0);
	}

	private void sendTeacherSchedule(int day) {
		TimeUnit t = TimeUnit.MILLISECONDS;
		long daySeconds = t.convert(1L, TimeUnit.DAYS);

		Date date = new Date(t.convert(currentMessage.date(), TimeUnit.SECONDS) + daySeconds * day);
		DayResponse groupSchedule = GetModels.getModels(Api.TEACHER_SCHEDULE + teacherId, DayResponse.class);
		System.out.println(groupSchedule);

		String markdown = groupSchedule.getDays().get(0).toString();
		bot.sendMessage(currentMessage.chat().id(), markdown, ParseMode.Markdown, null, null, null);

	}

	private void config() {
		String url = null;
		String sendMessage = "";
		if (currentMessage.text().startsWith("/settings")) {
			isConfig = false;
			settings.clear();

		}
		if (baseResponse != null) {
			String reply = currentMessage.text();
			if(settings.size() == 2){
				reply = currentMessage.text().replace(Emoji.values()[1].getSecondChar().toString(), "");
				System.out.println(currentMessage.text().indexOf(Emoji.values()[1].toString()));
			}
			String id = baseResponse.findId(reply);
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
			sendMessage = "Выберите форму обучения";	
			break;
		}

		Keyboard rkm = null;

		if (!isConfig) {
			baseResponse = GetModels.getModels(url);
			String[][] array = baseResponse.itemsToStringArray(2);
			
			rkm = new ReplyKeyboardMarkup(array, true, false, false);
			
			if(settings.size() == 2){
				String keycap = "KEYCAP";
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[i].length; j++) {
						String emoji = "";
						try{
							emoji = Emoji.valueOf(keycap + Integer.parseInt(array[i][j].substring(0, 1))).toString();
						}
						catch(Exception e){
							emoji = "";
						}
						array[i][j] =  array[i][j].replace(array[i][j].substring(0, 1), emoji);
					}
				}
			}
		} else {
			rkm = new ForceReply();
			sendMessage = currentMessage.text();
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
