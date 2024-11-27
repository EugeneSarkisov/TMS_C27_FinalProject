package com.teachmeskills.dating_app.util.validation;

import com.teachmeskills.dating_app.repository.UserAccountRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class Validation {
    @Autowired
    UserAccountRepository userAccountRepository;
    public boolean ifUsernameAlreadyExist(String username){
        if(userAccountRepository.findUserAccountByUsername(username) != null){
            return true;
        } else {
            return false;
        }
    }

    public boolean ifPasswordCorrect(String password){
        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")){
            return true;
        } else {
            return false;
        }
    }
    public boolean ifParamsCorrect(String firstName, String lastName, String email){
        if(firstName.matches("^(?=.*[a-z])(?=.*[A-Z]).{2,50}$") &&
                lastName.matches("^(?=.*[a-z])(?=.*[A-Z]).{2,50}$") &&
                email.matches("^(.+)@(.+)$"))
        {
            return true;
        } else {
            return false;
        }
    }
}
