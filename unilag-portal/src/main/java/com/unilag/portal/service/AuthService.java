package com.unilag.portal.service;

import com.unilag.portal.model.Course;
import com.unilag.portal.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Mock authentication + data layer.
 *
 * Everything lives in an in-memory Map, built once at startup, so no
 * database is required to run and demo the project. To go live with a
 * real DB: replace this class's internals with a StudentRepository
 * (Spring Data JPA) and hash passwords with BCrypt instead of storing
 * them in plain text.
 */
@Service
public class AuthService {

    private final Map<String, Student> students = new HashMap<>();

    public AuthService() {
        seedData();
    }

    /**
     * Attempts to log a student in.
     * @return the Student if credentials match, otherwise empty.
     */
    public Optional<Student> authenticate(String matricNo, String password) {
        Student student = students.get(matricNo.trim().toUpperCase());
        if (student != null && student.getPassword().equals(password)) {
            return Optional.of(student);
        }
        return Optional.empty();
    }

    public Optional<Student> findByMatricNo(String matricNo) {
        return Optional.ofNullable(students.get(matricNo.trim().toUpperCase()));
    }

    private void seedData() {
        List<Course> courses1 = List.of(
                new Course("CSC 301", "Data Structures & Algorithms", 3, "A"),
                new Course("CSC 305", "Software Engineering", 3, "B"),
                new Course("CSC 307", "Database Management Systems", 3, "A"),
                new Course("CSC 309", "Operating Systems", 2, "B"),
                new Course("MTH 301", "Numerical Analysis", 2, "C"),
                new Course("GST 301", "Entrepreneurship Studies", 2, "A")
        );

        Student student1 = new Student(
                "190805123", "portal123",
                "Adaeze Okonkwo", "Computer Science",
                "300 Level", "2025/2026",
                4.42, courses1
        );

        List<Course> courses2 = List.of(
                new Course("EEG 401", "Control Systems", 3, "B"),
                new Course("EEG 403", "Power Electronics", 3, "A"),
                new Course("EEG 405", "Digital Signal Processing", 3, "B"),
                new Course("EEG 407", "Engineering Management", 2, "A")
        );

        Student student2 = new Student(
                "180705089", "unilag2026",
                "Tunde Bakare", "Electrical/Electronics Engineering",
                "400 Level", "2025/2026",
                4.68, courses2
        );

        students.put(student1.getMatricNo(), student1);
        students.put(student2.getMatricNo(), student2);
    }
}
