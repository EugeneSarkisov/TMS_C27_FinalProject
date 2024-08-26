package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.user.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Integer> {

}
