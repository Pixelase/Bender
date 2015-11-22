package com.github.mickevichyura.grsu.api.utils;

public class Api {

	public static final String BASE_URL = "http://api.grsu.by/1.x/app1/";

	public static final String LECTURER_LIST = BASE_URL + "getTeachers?extended=false&teacherId=";

	public static final String DEPARTMENT_LIST = BASE_URL + "getDepartments";

	public static final String FACULTY_LIST = BASE_URL + "getFaculties";

	public static final String GROUP_LIST = BASE_URL + "getGroups?departmentId=2&facultyId=3&course=3";
	
	public static final String groupList(String departmentId, String facultyId, String course) {
		return BASE_URL + "getGroups?departmentId=" + departmentId + "&facultyId=" + facultyId + "&course=" + course;
	}
	
	public static final String groupSchedule(String groupId) {
		return BASE_URL + "getGroupSchedule?groupId=" + groupId + "&subgroupId=3690&dateStart=";
	}

	public static final String GROUP_SCHEDULE = BASE_URL + "getGroupSchedule?groupId=945&subgroupId=3690&dateStart=";
	
	public static final String COURSE_LIST = "https://gist.githubusercontent.com/MickevichYura/33b0f33a499c4ae8f295/raw/0f49dfd08daf665d3d56a621bc061173bcf74c0b/course.json";

}
