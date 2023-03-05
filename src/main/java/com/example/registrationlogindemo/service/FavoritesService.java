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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    public List<Favorites> findAll() {
        return favoritesRepository.findAll();
    }

    public List<Favorites> findByStatus(String status) {
        return favoritesRepository.findByStatus(status);
    }

//    public Favorites saveFavorites(Favorites favorites) {
//        return favoritesRepository.save(favorites);
//    }

    public void saveFavorites(Long userId, Long likedUserId, String status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        User likedUser = userRepository.findById(likedUserId).orElseThrow(() -> new UserNotFoundException(likedUserId));
        Favorites favorites = new Favorites(user.getId(), likedUser.getId(), status);
        favoritesRepository.save(favorites);
    }
}
