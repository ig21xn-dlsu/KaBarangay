package com.itismob.s03.group2.KaBarangay.models;

public class EquipmentItem {
	private final String category;
	private final String title;
	private final String description;
	private final String availability;
	private final int imageResId;

	public EquipmentItem(String category, String title, String description,
						 String availability, int imageResId) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.availability = availability;
		this.imageResId = imageResId;
	}

	public String getCategory() { return category; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public String getAvailability() { return availability; }
	public int getImageResId() { return imageResId; }
}
