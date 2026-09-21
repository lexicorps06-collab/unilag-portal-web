package com.unilag.portal.controller;

import com.unilag.portal.model.Student;
import com.unilag.portal.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class DashboardController {

    @Autowired
    private AuthService authService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Object matricAttr = session.getAttribute("loggedInMatric");

        // Guard: no active session -> bounce back to login
        if (matricAttr == null) {
            return "redirect:/login";
        }

        Optional<Student> studentOpt = authService.findByMatricNo(matricAttr.toString());
        if (studentOpt.isEmpty()) {
            session.invalidate();
            return "redirect:/login";
        }

        model.addAttribute("student", studentOpt.get());
        return "dashboard";
    }
}
