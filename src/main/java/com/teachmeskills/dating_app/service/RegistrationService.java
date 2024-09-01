package com.teachmeskills.dating_app.service;

import com.teachmeskills.dating_app.config.SecurityConfig;
import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.UserAccountRepository;
import com.teachmeskills.dating_app.util.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private SecurityConfig securityConfig;

    public int userAccountRegistration(String username, String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccountRepository.findUserAccountByUsername(username);
        userAccount.setPassword(securityConfig.passwordEncoder().encode(password));
        //TODO implement regex for password
        userAccount.setRole(Role.ROLE_USER.getAuthority());
        userAccountRepository.save(userAccount);
        return userAccount.getId();
    }

    public void userAccountFiller(int accountId, String firstName, String lastName, String email, int genderId) {
        UserAccount userAccount = userAccountRepository.findUserAccountById(accountId);
        userAccount.setUserAccountFirstName(firstName);
        userAccount.setUserAccountLastName(lastName);
        userAccount.setUserAccountEmail(email);
        //TODO implement regex for email
        userAccount.setUserAccountGenderId(genderId);
        //TODO implement right gender choose
        userAccountRepository.save(userAccount);
    }

    public boolean ifUsernameAlreadyExist(String username){
        if(userAccountRepository.findUserAccountByUsername(username) != null){
            return true;
        } else {
            return false;
        }
    }
}
