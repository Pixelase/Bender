package com.github.pixelase.bot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ModuleTask extends Task {
	protected List<UserTask> userTasks;
	protected ExecutorService userTaskExecutor;

	public ModuleTask() {
		this(new ArrayList<UserTask>(), Executors.newCachedThreadPool());
	}

	public ModuleTask(List<UserTask> userTasks, ExecutorService userTaskExecutor) {
		super();
		this.userTasks = userTasks;
		this.userTaskExecutor = userTaskExecutor;
	}
}
