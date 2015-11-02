package com.github.pixelase.bot.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pengrad.telegrambot.model.User;

public abstract class ModuleTask extends Task implements Configurable {
	protected List<UserTask> userTasks;
	protected ExecutorService userTaskExecutor;
	protected static long moduleTaskDelay;
	protected Class<? extends UserTask> userTaskClass;
	private String propFilePath;

	public ModuleTask(Class<? extends UserTask> userTaskClass) throws IOException {
		this(new ArrayList<UserTask>(), Executors.newCachedThreadPool(), userTaskClass);
	}

	private ModuleTask(List<UserTask> userTasks, ExecutorService userTaskExecutor,
			Class<? extends UserTask> userTaskClass) throws IOException {
		super();
		this.userTasks = userTasks;
		this.userTaskExecutor = userTaskExecutor;
		this.userTaskClass = userTaskClass;
		configure();
	}

	public static long getModuleTaskDelay() {
		return moduleTaskDelay;
	}

	public static void setModuleTaskDelay(long moduleTaskDelay) {
		ModuleTask.moduleTaskDelay = moduleTaskDelay;
	}

	private void startExecution(Class<? extends UserTask> userTaskClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		/*
		 * Temp variable
		 */
		UserTask currentUserTask = null;

		while (isOk) {
			/*
			 * Fix multithreading
			 */
			sleep(moduleTaskDelay);

			/*
			 * If message is null we can't get it fields, so skip this
			 * iteration;
			 */
			if (message == null) {
				continue;
			}

			/*
			 * Create currentUserTask with some user from message;
			 */
			currentUserTask = userTaskClass.getConstructor(User.class).newInstance(message.from());

			/*
			 * If the task for a given user is already on our list, skip this
			 * iteration;
			 */
			if (userTasks.contains(currentUserTask)) {
				continue;
			}

			/*
			 * Add task for a given user into our TaskList TODO we can use stack
			 * for this may be
			 */
			userTasks.add(currentUserTask);

			/*
			 * Update message field for all task's
			 */
			for (UserTask userTask : userTasks) {
				userTask.setMessage(message);
				userTask.setOk(isOk);
			}

			/*
			 * Add task to the executor
			 */
			userTaskExecutor.submit(userTasks.get(userTasks.size() - 1));
		}
	}

	@Override
	public void run() {
		try {
			startExecution(userTaskClass);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPropFilePath() {
		return propFilePath;
	}

	public void setPropFilePath(String propFilePath) {
		this.propFilePath = propFilePath;
	}
}
