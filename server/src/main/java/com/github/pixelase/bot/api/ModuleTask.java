package com.github.pixelase.bot.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.pengrad.telegrambot.model.User;

public abstract class ModuleTask extends Task implements Configurable {
	private Map<UserTask, Future<? extends UserTask>> userTasks;
	private ExecutorService userTaskExecutor;
	protected static long moduleTaskDelay;
	private Class<? extends UserTask> userTaskClass;

	public ModuleTask(Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
		this(new HashMap<UserTask, Future<? extends UserTask>>(), Executors.newCachedThreadPool(), userTaskClass,
				propFilePath);
	}

	private ModuleTask(Map<UserTask, Future<? extends UserTask>> userTasksMap, ExecutorService userTaskExecutor,
			Class<? extends UserTask> userTaskClass, String propFilePath) throws IOException {
		super();
		this.userTasks = userTasksMap;
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

	@SuppressWarnings("unchecked")
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
			if (commonMessage == null || !isMessageUpdated()) {
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
			if (userTasks.containsKey(currentUserTask)) {
				for (UserTask task : userTasks.keySet()) {
					if (task.equals(currentUserTask)) {
						task.setCurrentMessage(commonMessage);
						break;
					}
				}

				Future<? extends UserTask> future = userTasks.get(currentUserTask);

				if (future.isDone() || future.isCancelled()) {
					userTasks.remove(currentUserTask);
				} else {
					continue;
				}
			}

			/*
			 * Add task to the executor and add it to our map
			 */
			userTasks.put(currentUserTask, (Future<? extends UserTask>) userTaskExecutor.submit(currentUserTask));
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
