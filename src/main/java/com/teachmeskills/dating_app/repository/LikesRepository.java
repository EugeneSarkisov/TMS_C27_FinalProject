package com.teachmeskills.dating_app.repository;

import com.teachmeskills.dating_app.model.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    List<Integer> findAllByUserIdBy(int id);
    @Query(value ="INSERT INTO public.likes (user_id_by, user_id_target) VALUES (?1, ?2);" , nativeQuery = true)
    void addNewLike(int idBy, int idTarget);

    @Query(value = "SELECT likes.user_id_target FROM public.likes WHERE user_id_target = ?1", nativeQuery = true)
    List<Integer> showAllLikesOfUser(int id);

    @Query(value = "SELECT * FROM public.likes WHERE user_id_by = ?1", nativeQuery = true)
    List<Integer> showAllLikesFromUser(int id);
}
