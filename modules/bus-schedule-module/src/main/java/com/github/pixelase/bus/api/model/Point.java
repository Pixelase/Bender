package com.github.pixelase.bus.api.model;

public class Point {
	private String code;
	private String stationType;
	private String title;
	private String popularTitle;
	private String shortTitle;
	private String transportType;
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPopularTitle() {
		return popularTitle;
	}

	public void setPopularTitle(String popularTitle) {
		this.popularTitle = popularTitle;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Point [code=" + code + ", stationType=" + stationType + ", title=" + title + ", popularTitle="
				+ popularTitle + ", shortTitle=" + shortTitle + ", transportType=" + transportType + ", type=" + type
				+ "]";
	}
}