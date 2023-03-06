package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    List<Favorites> findByStatus(String status);

   // List<Favorites> findByLikedBy_IdAndLikedUser_Id(Long likedById, Long likedUserId);

    //List<Favorites> findByLikedByAndLikedUser(Long likedById, Long likedUserId);

    @Query("SELECT f FROM Favorites f WHERE f.likedBy.id = :likedById AND f.likedUser.id = :likedUserId")
    List<Favorites> findByLikedByAndLikedUser(@Param("likedById") Long likedById, @Param("likedUserId") Long likedUserId);
}


