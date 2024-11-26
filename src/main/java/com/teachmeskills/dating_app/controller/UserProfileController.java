package com.teachmeskills.dating_app.controller;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.*;
import com.teachmeskills.dating_app.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/dating")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private HobbiesRepository hobbiesRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private UserPhotoRepository userPhotoRepository;
    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private LikesRepository likesRepository;

    @GetMapping("/profile")
    public ModelAndView fillProfilePage(@RequestParam(value = "user_id", required = false) Integer userId) {
        if (userId == null) {
            return new ModelAndView("profile", userProfileService.getUserAccountParams(userProfileService.getAuthUser()));
        } else if (userId != userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId()) {
            if (likesRepository.isExists(userId, userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId()) &&
                    likesRepository.isExists(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId(), userId)) {
                return new ModelAndView("profile", userProfileService.getUserAccountParams(userAccountRepository.findUserAccountById(userId).getUsername()));
            } else {
                return null;
            }
        } else {
            return new ModelAndView("profile", userProfileService.getUserAccountParams(userProfileService.getAuthUser()));
        }
    }

    @GetMapping("/settings")
    public ModelAndView showSettingsPage(Model model) {
        model.addAttribute("hobbiesList", (hobbiesRepository.viewHobbies()));
        return new ModelAndView("settings", userProfileService.getUserAccountParams(userProfileService.getAuthUser()));
    }

    @PostMapping("/change_user_info")
    public String updateUserInfo(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                 @RequestParam("email") String email, @RequestParam("aboutMe") String aboutMe,
                                 @RequestParam("gender") String gender) {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser());
        userAccount = userProfileService.userAccountUpdate(userProfileService.setNewUserAccountParams(userAccount, firstName, lastName, email, aboutMe, genderRepository.getGenderIdByName(gender)));
        userAccountRepository.save(userAccount);
        return "redirect:/dating/settings";
    }

    @PostMapping("/change_password")
    public String updateUserPassword(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword) throws Exception {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser());
        userProfileService.changeUserPassword(userAccount, oldPassword, newPassword);
        return "redirect:/dating/settings";
    }

    @PostMapping("/add_user_hobby")
    public String addUserHobby(@RequestParam("hobbie") String hobby){
        int userId = userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId();
        int hobbyId = hobbiesRepository.getHobbyIdByName(hobby);
        hobbiesRepository.addUserHobbie(userId, hobbyId);
        return "redirect:/dating/settings";
    }

    @PostMapping("/remove_user_hobby")
    public String removeUserHobby(@RequestParam("hobbieDelete") String hobby){
        int userId = userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId();
        int hobbyId = hobbiesRepository.getHobbyIdByName(hobby);
        hobbiesRepository.deleteUserHobbie(userId, hobbyId);
        return "redirect:/dating/settings";
    }

    @PostMapping("/change_user_photo")
    public String changeUserPhoto(@RequestParam("photo") MultipartFile file) throws IOException {
        if (userPhotoRepository.getUserPhotoByUserAccountId(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId()) == null) {
            userPhotoRepository.uploadFirstUserPhoto(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId(),
                    userProfileService.uploadUserPhoto(file));
        } else {
            userPhotoRepository.updateUserPhoto(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId(),
                    userProfileService.uploadUserPhoto(file));
        }
        return "redirect:/dating/settings";
    }

    @PostMapping("/change_user_dating_param")
    public String changeUserPhoto(@RequestParam("gender") String gender, @RequestParam("relation") String relationName){
        userProfileService.changeUserDatingParam(userAccountRepository.findUserAccountByUsername(userProfileService.getAuthUser()).getId(),
                genderRepository.getGenderIdByName(gender), relationRepository.getRelationTypeIdByName(relationName));
        return "redirect:/dating/settings";
    }
}