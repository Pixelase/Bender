package com.github.pixelase.bot.api;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.User;

public abstract class UserTask extends Task {

	protected final User user;
	protected Message currentMessage;
	protected static long userTaskDelay;
	protected static long userTaskTimeout;

	public UserTask(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Message getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(Message currentMessage) {
		this.currentMessage = currentMessage;
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

	public static long getUserTaskDelay() {
		return userTaskDelay;
	}

	public static void setUserTaskDelay(long userTaskDelay) {
		UserTask.userTaskDelay = userTaskDelay;
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
