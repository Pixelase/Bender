package com.github.mickevichyura.grsu.api.model;

import java.util.List;

public class Day {

	private String num;
	private Integer count;
	private String date;
	private List<Lesson> lessons;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

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
