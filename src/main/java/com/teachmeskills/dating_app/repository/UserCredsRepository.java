package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.model.user.UserCreds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredsRepository extends JpaRepository<UserCreds, Integer> {
    UserCreds findUserCredsById(UserAccount userAccount);
}
