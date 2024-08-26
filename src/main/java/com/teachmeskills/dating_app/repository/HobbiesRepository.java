package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.hobbies.HobbiesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbiesRepository extends JpaRepository<HobbiesType, Integer> {

}
