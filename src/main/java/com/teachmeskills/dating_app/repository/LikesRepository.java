package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.likes.Likes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    List<Integer> findAllByUserIdBy(int id);
    @Modifying
    @Transactional
    @Query(value ="INSERT INTO public.likes (user_id_by, user_id_target) VALUES (?1, ?2);" , nativeQuery = true)
    void addNewLike(int idBy, int idTarget);

    @Query(value = "SELECT likes.user_id_target FROM public.likes WHERE user_id_by = ?1", nativeQuery = true)
    List<Integer> showAllLikesOfUser(int id);

    @Query(value = "SELECT likes.user_id_by FROM public.likes WHERE user_id_target = ?1", nativeQuery = true)
    List<Integer> showAllLikesFromUser(int id);
    @Query(value = "SELECT EXISTS(SELECT ?1 FROM likes WHERE user_id_by = ?2) AS \"exists\";", nativeQuery = true)
    boolean isExists(int idBy, int idTarget);
}
