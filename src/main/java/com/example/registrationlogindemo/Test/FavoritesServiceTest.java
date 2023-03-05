//package com.example.registrationlogindemo.Test;
//
//import com.example.registrationlogindemo.entity.Favorites;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.FavoritesRepository;
//import com.example.registrationlogindemo.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class FavoritesServiceTest {
//
//    private FavoritesService favoritesService;
//
//    @Mock
//    private FavoritesRepository favoritesRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        favoritesService = new FavoritesService(favoritesRepository, userRepository);
//    }
//
//    @Test
//    public void testFindAll() {
//        List<Favorites> favoritesList = new ArrayList<>();
//        Favorites favorites1 = new Favorites(new User("user1"), new User("user2"), "like");
//        Favorites favorites2 = new Favorites(new User("user3"), new User("user4"), "dislike");
//        favoritesList.add(favorites1);
//        favoritesList.add(favorites2);
//        when(favoritesRepository.findAll()).thenReturn(favoritesList);
//        assertEquals(favoritesList, favoritesService.findAll());
//        verify(favoritesRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindByStatus() {
//        List<Favorites> favoritesList = new ArrayList<>();
//        Favorites favorites1 = new Favorites(new User("user1"), new User("user2"), "like");
//        Favorites favorites2 = new Favorites(new User("user3"), new User("user4"), "like");
//        favoritesList.add(favorites1);
//        favoritesList.add(favorites2);
//        when(favoritesRepository.findByStatus("like")).thenReturn(favoritesList);
//        assertEquals(favoritesList, favoritesService.findByStatus("like"));
//        verify(favoritesRepository, times(1)).findByStatus("like");
//    }
//
//    @Test
//    public void testSaveFavorites() {
//        Favorites favorites = new Favorites(new User("user1"), new User("user2"), "like");
//        when(favoritesRepository.save(favorites)).thenReturn(favorites);
//        assertEquals(favorites, favoritesService.saveFavorites(favorites));
//        verify(favoritesRepository, times(1)).save(favorites);
//    }
//
//}
