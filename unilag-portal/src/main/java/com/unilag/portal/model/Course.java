package com.unilag.portal.model;

/**
 * Represents a single registered course and its result for a semester.
 */
public class Course {
    private String code;
    private String title;
    private int units;
    private String grade;

    public Course(String code, String title, int units, String grade) {
        this.code = code;
        this.title = title;
        this.units = units;
        this.grade = grade;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getUnits() { return units; }
    public String getGrade() { return grade; }
}
