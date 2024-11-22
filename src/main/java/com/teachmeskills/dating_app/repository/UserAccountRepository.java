package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findUserAccountById(int id);
    UserAccount findUserAccountByUsername(String username);
    List<UserAccount> findAllByIdAfter(int id);

}
