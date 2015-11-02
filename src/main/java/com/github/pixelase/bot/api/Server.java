package com.github.pixelase.bot.api;

public interface Server extends Configurable {
	public void start();

	public void stop();

	public void refresh();

}
