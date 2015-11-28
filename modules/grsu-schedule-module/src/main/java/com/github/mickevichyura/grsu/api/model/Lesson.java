package com.github.mickevichyura.grsu.api.model;

public class Lesson {

	private String timeStart;
	private String timeEnd;
	private Teacher teacher;
	private String type;
	private String title;
	private String address;
	private String room;
	private Subgroup subgroup;

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Subgroup getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(Subgroup subgroup) {
		this.subgroup = subgroup;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(timeStart + " - " + timeEnd + ": ").append("\n");
		sb.append(title);
		sb.append(" ").append(type);
		sb.append("\n").append(address);
		sb.append(" -- ").append(room);
		sb.append("\n").append(teacher);
		sb.append("\n").append(subgroup);

		if(!"".equals(subgroup.getTitle())){
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
