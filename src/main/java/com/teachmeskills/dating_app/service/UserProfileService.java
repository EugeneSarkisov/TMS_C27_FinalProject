package com.teachmeskills.dating_app.service;

import com.teachmeskills.dating_app.config.SecurityConfig;
import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileService {
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    SecurityConfig securityConfig;
    private UserAccount updateUserAccount(UserAccount userAccount) {
        UserAccount existentUserAccount = userAccountRepository.findUserAccountById(userAccount.getId());
        existentUserAccount.setUserAccountAboutMe(userAccount.getUserAccountAboutMe());
        existentUserAccount.setUserAccountEmail(userAccount.getUserAccountEmail());
        existentUserAccount.setUserAccountAboutMe(userAccount.getUserAccountAboutMe());
        //TODO all methods
        return userAccountRepository.save(userAccount);
    }
}
