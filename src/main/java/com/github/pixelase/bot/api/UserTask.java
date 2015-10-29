package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.User;

public abstract class UserTask extends Task {
	protected final User user;

	public UserTask(User user) {
		this(user, null, true);
	}

	public UserTask(User user, Message message, boolean isOk) {
		super();
		this.user = user;
		this.message = message;
		this.isOk = isOk;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTask other = (UserTask) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
