package com.github.pixelase.bot.modules.weather;

import com.github.pixelase.bot.api.UserTask;
import com.pengrad.telegrambot.model.User;

public class WeatherUserTask extends UserTask {

	public WeatherUserTask(User user) {
		super(user);
	}

	@Override
	public void run() {
		System.out.println("ivan govnov");
//		System.out.printf("From %s task(%s): %s\n", user.username(), this.hashCode(), message.text());
	}

}
