package com.github.mickevichyura.grsu.api.model;

import java.util.List;

public class Day {

	private String date;
	private List<Lesson> lessons;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(date + "\n");

		for (Lesson lesson : lessons) {
			if (!(lesson.getTitle().contains("Физическая культура")
					|| lesson.getTitle().contains("Русский язык как иностранный")))
				sb.append(lesson).append("\n");
		}

		return sb.toString();
	}

}
