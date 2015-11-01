package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.User;

public abstract class UserTask extends Task {

	protected final User user;
	protected static long userTaskTimeout;

	public UserTask(User user) {
		super();
		this.user = user;
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

	public static long getTaskTimeout() {
		return userTaskTimeout;
	}

	public static void setTaskTimeout(long taskTimeout) {
		UserTask.userTaskTimeout = taskTimeout;
	}

	public static long getUserTaskTimeout() {
		return userTaskTimeout;
	}

	public static void setUserTaskTimeout(long userTaskTimeout) {
		UserTask.userTaskTimeout = userTaskTimeout;
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
