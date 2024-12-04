package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.user.UserPhoto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Integer> {
    @Query(value = "SELECT user_photo.id, user_photo.link, user_photo.user_account_id FROM user_photo JOIN user_account ON user_photo.user_account_id = user_account.id WHERE user_account_id = ?1", nativeQuery = true)
    UserPhoto getUserPhotoByUserAccountId(int id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_photo (user_account_id, link) VALUES (:id, :link)", nativeQuery = true)
    void uploadFirstUserPhoto(int id, String link);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_photo SET link = :link WHERE user_account_id = :id", nativeQuery = true)
    void updateUserPhoto(int id, String link);
}
