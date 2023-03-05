package com.example.registrationlogindemo.entity;

import lombok.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long likedById;

    @Column(name = "liked_user_id")
    private Long likedUserId;

    private String status;

    public Favorites() {}

    public Favorites(Long likedById, Long likedUserId, String status) {
        this.likedById = likedById;
        this.likedUserId = likedUserId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikedById() {
        return likedById;
    }

    public void setLikedById(Long likedById) {
        this.likedById = likedById;
    }

    public Long getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(Long likedUserId) {
        this.likedUserId = likedUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(id, favorites.id) && Objects.equals(likedById, favorites.likedById) && Objects.equals(likedUserId, favorites.likedUserId) && Objects.equals(status, favorites.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likedById, likedUserId, status);
    }
}
