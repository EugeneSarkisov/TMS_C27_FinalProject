package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.relation.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<RelationType, Integer> {

}
