//package com.example.registrationlogindemo.entity;
//
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import java.io.Serializable;
//import java.util.Objects;
//@Embeddable
//public class FavoritesId implements Serializable {
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "liked_user_id")
//    private User likedUser;
//
//    public FavoritesId() {}
//
//    public FavoritesId(User user, User likedUser) {
//        this.user = user;
//        this.likedUser = likedUser;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public User getLikedUser() {
//        return likedUser;
//    }
//
//    public void setLikedUser(User likedUser) {
//        this.likedUser = likedUser;
//    }
//
//    // override equals() and hashCode() methods here
//}
