package com.teachmeskills.dating_app.controller;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.UserAccountRepository;
import com.teachmeskills.dating_app.service.DatingAppService;
import com.teachmeskills.dating_app.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/dating")
public class DatingAppController {
    @Autowired
    DatingAppService datingAppService;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/search")
    public ModelAndView showAcceptableUsers() {
        return new ModelAndView("/dating", "users", datingAppService.showMatesForUser(userAccountRepository.
                findUserAccountByUsername(userProfileService.getAuthUser())));

    }

    @PostMapping("like")
    public String likeUser(@RequestParam("userId") int id) {
        datingAppService.likeUser(userAccountRepository.
                findUserAccountByUsername(userProfileService.getAuthUser()).getId(), id);
        return "redirect:/dating/search";
    }

    @GetMapping("/connections")
    public String listOfLikes(Model model) {
        List<UserAccount> userAccountWho = datingAppService.showWhoLikeThisUser(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()));
        List<UserAccount> userAccountByUsers = datingAppService.showLikesFromUsers(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()));
        List<UserAccount> matches = datingAppService.showMatches(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()));
        model.addAttribute("youLike", userAccountWho);
        model.addAttribute("youWereLikedBy", userAccountByUsers);
        model.addAttribute("matches", matches);
        return "connections";
    }
}

