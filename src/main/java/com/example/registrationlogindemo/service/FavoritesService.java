package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.Exception.UserNotFoundException;
import com.example.registrationlogindemo.entity.Favorites;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FavoritesRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;

    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    public void saveFavorites(Long likedById, Long likedUserId, String status) {
        List<Favorites> existingFavorites = favoritesRepository.findByLikedByAndLikedUser(likedById, likedUserId);
        if (!existingFavorites.isEmpty()) {
            Favorites favorites = existingFavorites.get(0);
            favorites.setStatus(status);
            System.out.println("-----------------------");
            favoritesRepository.save(favorites);
        }
    }

    public List<Favorites> findByStatus(String status) {
        return favoritesRepository.findByStatus(status);
    }

    public List<Favorites> findByStatusAndLikedBy(String like, Long currentUserId) {
        return favoritesRepository.findByStatusAndLikedBy(like, currentUserId);
    }

    public Favorites findByLikedUserAndLikedBy(Long userId, Long currentUserId) {
        return favoritesRepository.findByLikedUserAndLikedBy(userId, currentUserId);
    }

    public void delete(Favorites favorite) {
        favoritesRepository.delete(favorite);
    }
}
