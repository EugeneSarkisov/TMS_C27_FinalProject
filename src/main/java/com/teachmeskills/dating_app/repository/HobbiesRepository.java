package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.hobbies.HobbiesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HobbiesRepository extends JpaRepository<HobbiesType, Integer> {
    @Query(value = "SELECT hobbie_name FROM hobbies  JOIN user_hobbies ON hobbies.id = user_hobbies.hobbie_id  JOIN user_account ON user_hobbies.user_account_id = user_account.id WHERE user_account_id = ?1", nativeQuery = true)
    List<String> getUserHobbiesById(int id);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.user_hobbies (user_account_id, hobbie_id) VALUES (:userId, :hobbieId)", nativeQuery = true)
    void addUserHobbie(int userId, int hobbieId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.user_hobbies WHERE user_account_id = ?1 AND hobbie_id = ?2", nativeQuery = true)
    void deleteUserHobbie(int userId, int hobbieId);
    @Query(value = "SELECT hobbie_name FROM hobbies", nativeQuery = true)
    List<String> viewHobbies();
    @Query(value = "SELECT id FROM hobbies", nativeQuery = true)
    List<Integer> getHobbiesId();
    @Query(value = "SELECT hobbie_id FROM user_hobbies", nativeQuery = true)
    List<Integer> getUserHobbiesId();
    @Query(value = "SELECT id FROM hobbies WHERE hobbies.hobbie_name = ?1", nativeQuery = true)
    int getHobbyIdByName(String name);


}
