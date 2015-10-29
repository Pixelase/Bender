package com.github.pixelase.bot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pengrad.telegrambot.model.Message;

public abstract class Module implements Runnable {
	protected List<UserTask> userTasks;
	protected ExecutorService userTaskExecutor;
	protected Message message;
	protected boolean isOk;

	public Module() {
		this(new ArrayList<UserTask>(), Executors.newCachedThreadPool(), true);
	}

	public Module(List<UserTask> userTasks, ExecutorService userTaskExecutor, boolean isOk) {
		super();
		this.userTasks = userTasks;
		this.userTaskExecutor = userTaskExecutor;
		this.isOk = isOk;
	}

	/**
	 * Update message field
	 * 
	 * @param new
	 *            Message object
	 */
	public void updateMessage(Message message) {
		this.message = message;
	}

	public void updateState(boolean isOk) {
		this.isOk = isOk;
	}

	@Override
	public void run() {
		while (isOk) {
			// TODO If new user comes, create new UserTask and submit it to
			// the userTaskExecutor

			// userTaskExecutor.submit(userTask);
		}
	}
}
