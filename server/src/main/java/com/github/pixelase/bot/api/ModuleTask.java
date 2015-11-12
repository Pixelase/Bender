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

	public ModuleTask(Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
		this(new ArrayList<UserTask>(), Executors.newCachedThreadPool(), userTaskClass, propFilePath);
	}

	private ModuleTask(List<UserTask> userTasks, ExecutorService userTaskExecutor,
			Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
		super();
		this.userTasks = userTasks;
		this.userTaskExecutor = userTaskExecutor;
		this.userTaskClass = userTaskClass;
		configure(propFilePath);
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
			if (commonMessage == null) {
				continue;
			}

			/*
			 * Create currentUserTask with some user from message;
			 */
			currentUserTask = userTaskClass.getConstructor(User.class).newInstance(commonMessage.from());
			currentUserTask.setCurrentMessage(commonMessage);

			/*
			 * If the task for a given user is already on our list, skip this
			 * iteration;
			 */
			if (userTasks.contains(currentUserTask)) {
				userTasks.get(userTasks.indexOf(currentUserTask)).setCurrentMessage(commonMessage);
				continue;
			}

			/*
			 * Add task for a given user into our TaskList TODO we can use stack
			 * for this may be
			 */
			userTasks.add(currentUserTask);

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
}
