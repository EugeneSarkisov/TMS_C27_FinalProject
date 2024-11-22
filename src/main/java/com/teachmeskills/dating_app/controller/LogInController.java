
package com.teachmeskills.dating_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
    @GetMapping("/dating/login")
    public String loginProc(Model model){
        return "login";
    }
}
