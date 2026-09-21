package com.unilag.portal.model;

import java.util.List;

/**
 * Represents a student account: login credentials + profile + academic records.
 * In this mock version data lives in memory (see AuthService).
 * Swap this out for a JPA @Entity backed by MySQL later if a real DB is needed.
 */
public class Student {
    private String matricNo;
    private String password;
    private String fullName;
    private String department;
    private String level;      // e.g. "300 Level"
    private String session;    // e.g. "2025/2026"
    private double gpa;
    private List<Course> courses;

    public Student(String matricNo, String password, String fullName, String department,
                    String level, String session, double gpa, List<Course> courses) {
        this.matricNo = matricNo;
        this.password = password;
        this.fullName = fullName;
        this.department = department;
        this.level = level;
        this.session = session;
        this.gpa = gpa;
        this.courses = courses;
    }

    public String getMatricNo() { return matricNo; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public String getLevel() { return level; }
    public String getSession() { return session; }
    public double getGpa() { return gpa; }
    public List<Course> getCourses() { return courses; }

    /** Total registered units, computed from the course list. */
    public int getTotalUnits() {
        return courses.stream().mapToInt(Course::getUnits).sum();
    }
}
