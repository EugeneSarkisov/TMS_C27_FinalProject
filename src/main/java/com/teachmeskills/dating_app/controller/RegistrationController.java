
package com.teachmeskills.dating_app.controller;

import com.teachmeskills.dating_app.service.RegistrationService;
import com.teachmeskills.dating_app.util.validation.Validation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dating")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @GetMapping("/registration")
    public String showRegPage() {
        return "registration";
    }
    @PostMapping("/registration")
    public String regNewUser(String username, String password, HttpSession session) {
        boolean ifUsernameExist = Validation.ifUsernameAlreadyExist(username);
        boolean ifPasswordCorrect = Validation.ifPasswordCorrect(password);
        if (ifUsernameExist) {
            return "redirect:/dating/registration?error";
        } else if(!ifPasswordCorrect){
            return "redirect:/dating/registration?errorPassword";
        } else {
            int accountId = registrationService.userAccountRegistration(username, password);
            session.setAttribute("accountId", accountId);
            return "redirect:/dating/registration_continue";
        }
    }

    @GetMapping("/registration_continue")
    public String showSecRegPage() {
        return "registration_continue";
    }

    @PostMapping("/registration_continue")
    public String fillNewUser(String firstName, String lastName, String email, String gender, HttpSession session) {
        boolean ifParamsCorrect = Validation.ifParamsCorrect(firstName, lastName, email);
        if(ifParamsCorrect){
            int accountId = (int) session.getAttribute("accountId");
            registrationService.userAccountFiller(accountId, firstName, lastName, email, gender);
            session.removeAttribute("accountId");
            return "redirect:/dating/login";
        } else{
           return  "redirect:/dating/registration_continue?error";
        }
    }
}
