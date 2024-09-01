package com.teachmeskills.dating_app.controller;

import com.teachmeskills.dating_app.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;


    @GetMapping("/dating/registration")
    public String showRegPage() {
        return "registration";
    }

    //TODO send an email

    @PostMapping("/dating/registration")
    public String regNewUser(String username, String password, HttpSession session) {
        boolean ifUsernameExist = registrationService.ifUsernameAlreadyExist(username);
        if (ifUsernameExist) {
            return "redirect:/dating/registration?error";
        } else {
            int accountId = registrationService.userAccountRegistration(username, password);
            session.setAttribute("accountId", accountId);
            return "redirect:/dating/registration_continue";
        }
    }

    @GetMapping("/dating/registration_continue")
    public String showSecRegPage() {
        return "registration_continue";
    }

    @PostMapping("/dating/registration_continue")
    public String fillNewUser(String firstName, String lastName, String email, int genderId, HttpSession session) {
        int accountId = (int) session.getAttribute("accountId");
        registrationService.userAccountFiller(accountId, firstName, lastName, email, genderId);
        session.removeAttribute("accountId");
        return "redirect:/dating/login";
    }
}
