package com.unilag.portal.controller;

import com.unilag.portal.model.Student;
import com.unilag.portal.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    /** Shows the login page. If already logged in, skip straight to dashboard. */
    @GetMapping({"/", "/login"})
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loggedInMatric") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    /** Handles the login form submission. */
    @PostMapping("/login")
    public String processLogin(@RequestParam String matricNo,
                                @RequestParam String password,
                                HttpServletRequest request,
                                Model model) {

        Optional<Student> result = authService.authenticate(matricNo, password);

        if (result.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInMatric", result.get().getMatricNo());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid matric number or password. Please try again.");
        model.addAttribute("matricNo", matricNo);
        return "login";
    }

    /** Logs the student out and destroys the session. */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?loggedOut";
    }
}
