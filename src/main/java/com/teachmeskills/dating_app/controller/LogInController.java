
package com.teachmeskills.dating_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
    @GetMapping("/dating/login")
    public String loginProc(Model model) {
        return "login";
    }

    @GetMapping("/dating/logout")
    public String logoutProc(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/dating/login";
    }
}
