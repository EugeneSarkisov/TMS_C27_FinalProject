package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.gender.GenderType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderType, Integer> {
    @Query(value = "SELECT id FROM gender WHERE gender.gender_name = ?1", nativeQuery = true)
    int getGenderIdByName(String name);
    @Query(value = "SELECT gender.gender_name FROM gender JOIN interested_in_gender ON gender.id = interested_in_gender.id JOIN user_account ON interested_in_gender.user_account_id = user_account.id WHERE user_account.id = ?1;", nativeQuery = true)
    String getInterestedInGenderName(int id);
    @Query(value = "SELECT gender_id FROM interested_in_gender WHERE user_account_id = ?1", nativeQuery = true)
    int getInterestedInGenderId(int id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO interested_in_gender (user_account_id, gender_id)  VALUES (?1, ?2)", nativeQuery = true)
    void setInterestedInGenderId(int id, int gender_id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE interested_in_gender SET gender_id = :gender_id WHERE user_account_id = :id", nativeQuery = true)
    void updateInterestedInGenderId(int id, int gender_id);

}
