package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.relation.RelationType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<RelationType, Integer> {
    @Query(value = "SELECT name FROM relation_type JOIN interested_in_relation ON relation_type.id = interested_in_relation.relation_type_id JOIN user_account ON interested_in_relation.user_account_id = user_account.id WHERE user_account_id = ?1", nativeQuery = true)
    String getUserRelationType(int id);
    @Query(value = "SELECT id FROM relation_type WHERE name = ?1", nativeQuery = true)
    int getRelationTypeIdByName(String name);
    @Query(value ="SELECT relation_type_id FROM interested_in_relation WHERE user_account_id = ?1",nativeQuery = true)
    int findById(int id);
    @Query(value = "SELECT name FROM relation_type WHERE id = ?1", nativeQuery = true)
    String getNameById(int id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO interested_in_relation (user_account_id, relation_type_id)  VALUES (?1, ?2)", nativeQuery = true)
    void setInterestedInRelationId(int id, int relation_id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE interested_in_relation SET relation_type_id = :relation_id WHERE user_account_id = :id", nativeQuery = true)
    void updateInterestedInRelationId(int id, int relation_id);
}
