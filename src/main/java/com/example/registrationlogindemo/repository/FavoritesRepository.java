package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findByStatus(String status);
}


