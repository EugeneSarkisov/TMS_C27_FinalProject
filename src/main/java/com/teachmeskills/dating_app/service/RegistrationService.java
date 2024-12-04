
package com.teachmeskills.dating_app.service;

import com.teachmeskills.dating_app.config.SecurityConfig;
import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.GenderRepository;
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
    @Autowired
    private GenderRepository genderRepository;

    public int userAccountRegistration(String username, String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccountRepository.findUserAccountByUsername(username);
        userAccount.setPassword(securityConfig.passwordEncoder().encode(password));
        userAccount.setRole(Role.ROLE_USER.getAuthority());
        userAccountRepository.save(userAccount);
        return userAccount.getId();
    }

    public void userAccountFiller(int accountId, String firstName, String lastName, String email, String gender) {
        UserAccount userAccount = userAccountRepository.findUserAccountById(accountId);
        userAccount.setUserAccountFirstName(firstName);
        userAccount.setUserAccountLastName(lastName);
        userAccount.setUserAccountEmail(email);
        userAccount.setUserAccountGenderId(genderRepository.getGenderIdByName(gender));
        userAccountRepository.save(userAccount);
    }
}
