package com.teachmeskills.dating_app.service;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.*;
import com.teachmeskills.dating_app.util.consts.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserProfileService {
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    HobbiesRepository hobbiesRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    UserPhotoRepository userPhotoRepository;
    @Autowired
    GenderRepository genderRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);


    public UserAccount userAccountUpdate(UserAccount userAccount) {
        UserAccount existentUserAccount = userAccountRepository.findUserAccountById(userAccount.getId());
        existentUserAccount.setUserAccountFirstName(userAccount.getUserAccountFirstName());
        existentUserAccount.setUserAccountLastName(userAccount.getUserAccountLastName());
        existentUserAccount.setUserAccountEmail(userAccount.getUserAccountEmail());
        existentUserAccount.setUserAccountAboutMe(userAccount.getUserAccountAboutMe());
        existentUserAccount.setUserAccountGenderId(userAccount.getUserAccountGenderId());
        return userAccountRepository.save(userAccount);
    }

    public Map<String, Object> getUserAccountParams(String username) {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(username);
        Map<String, Object> userParam = new HashMap<>();
        userParam.put("userFirstName", userAccount.getUserAccountFirstName());
        userParam.put("userLastName", userAccount.getUserAccountLastName());
        userParam.put("userAboutMe", userAccount.getUserAccountAboutMe());
        userParam.put("userEmail", userAccount.getUserAccountEmail());
        userParam.put("userGender", userAccount.getUserAccountGenderId());
        userParam.put("userHobbies", hobbiesRepository.getUserHobbiesById(userAccount.getId()));
        userParam.put("userRelationType", relationRepository.getUserRelationType(userAccount.getId()));
        try{
            userParam.put("userPhoto", userPhotoRepository.getUserPhotoByUserAccountId(userAccount.getId()).getPhotoLink());
        } catch (NullPointerException exception){
            userParam.put("userPhoto", "null");
        }
        return userParam;
    }

    public UserAccount setNewUserAccountParams(UserAccount userAccount, String name, String surname, String email, String aboutMe, int genderId) {
        userAccount.setUserAccountFirstName(name);
        userAccount.setUserAccountLastName(surname);
        userAccount.setUserAccountEmail(email);
        userAccount.setUserAccountAboutMe(aboutMe);
        userAccount.setUserAccountGenderId(genderId);
        return userAccount;
    }

    public String getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public void changeUserPassword(UserAccount userAccount, String userPassword, String userNewPassword) throws Exception {
        String hashedPassword = bCryptPasswordEncoder.encode(userPassword);
        if (bCryptPasswordEncoder.matches(userPassword, hashedPassword)) {
            userAccount.setPassword(bCryptPasswordEncoder.encode(userNewPassword));
            userAccountRepository.save(userAccount);
        } else {
            throw new Exception("Password isn't compare!");
        }
    }
    public String uploadUserPhoto(MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(Const.UPLOAD_FILE_DIRECTION, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        return fileNameAndPath.toString().substring(25).replace(File.separator, "/");
    }

    public void changeUserDatingParam(int id, int gender_id, int relation_type_id){
        if(genderRepository.getInterestedInGenderName(id) == null){
            genderRepository.setInterestedInGenderId(id, gender_id);
        } else {
            genderRepository.updateInterestedInGenderId(id, gender_id);
        }
        if(relationRepository.getUserRelationType(id) == null){
            relationRepository.setInterestedInRelationId(id, relation_type_id);
        } else {
            relationRepository.updateInterestedInRelationId(id, relation_type_id);
        }
    }
}
