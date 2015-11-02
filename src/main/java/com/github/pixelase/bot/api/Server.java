package com.github.pixelase.bot.api;

public interface Server extends Configurable {
	public void start() throws InterruptedException;

	public void stop() throws InterruptedException;

	public void refresh() throws InterruptedException;

}
