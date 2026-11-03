package com.itismob.s03.group2.KaBarangay.models;

public class Hotline {
    private String category;
    private String title;
    private String description;
    private String badgeText;
    private String numberLabel;
    private String number;
    private int iconResId;

    public Hotline(String category, String title, String description, String badgeText, String numberLabel, String number, int iconResId) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.badgeText = badgeText;
        this.numberLabel = numberLabel;
        this.number = number;
        this.iconResId = iconResId;
    }

    public String getCategory() { return category; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getBadgeText() { return badgeText; }
    public String getNumberLabel() { return numberLabel; }
    public String getNumber() { return number; }
    public int getIconResId() { return iconResId; }
}