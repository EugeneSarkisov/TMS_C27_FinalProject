package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.gender.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderType, Integer> {

}
