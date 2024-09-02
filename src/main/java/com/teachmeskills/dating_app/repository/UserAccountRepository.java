package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findUserAccountById(int id);
    UserAccount findUserAccountByUsername(String username);

}
