package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    public List<Favorites> findAll() {
        return favoritesRepository.findAll();
    }

    public List<Favorites> findByStatus(String status) {
        return favoritesRepository.findByStatus(status);
    }

    public Favorites saveFavorites(Favorites favorites) {
        return favoritesRepository.save(favorites);
    }
}