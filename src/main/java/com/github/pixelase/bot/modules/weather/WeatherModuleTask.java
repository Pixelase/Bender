package com.github.pixelase.bot.modules.weather;

import com.github.pixelase.bot.api.ModuleTask;
import com.github.pixelase.bot.api.UserTask;

public class WeatherModuleTask extends ModuleTask {

	public WeatherModuleTask() {
		super();
	}

	@Override
	public void run() {
		/*
		 * Temp variable
		 */
		UserTask currentUserTask = null;

		while (isOk) {
			/*
			 * Fix multithreading
			 */
			sleep(moduleTaskTimeout);

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
			currentUserTask = new WeatherUserTask(message.from());

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

}
