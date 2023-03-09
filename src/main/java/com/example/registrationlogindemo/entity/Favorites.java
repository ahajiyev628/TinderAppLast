package com.example.registrationlogindemo.entity;

import lombok.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This can be either "like" or "dislike"
    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", referencedColumnName = "id")
    private User likedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", referencedColumnName = "id")
    private User likedUser;

    @ManyToMany(mappedBy="favorites")
    private List<User> users;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "messages_favorites",
            joinColumns = { @JoinColumn(name = "user2_id") },
            inverseJoinColumns = { @JoinColumn(name = "msg_id") }
    )
    private List<Message> messages = new ArrayList<>();

    public Favorites(String status, User likedBy, User likedUser) {
        this.status = status;
        this.likedBy = likedBy;
        this.likedUser = likedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(User likedBy) {
        this.likedBy = likedBy;
    }

    public User getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(User likedUser) {
        this.likedUser = likedUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(id, favorites.id) && Objects.equals(status, favorites.status) && Objects.equals(likedBy, favorites.likedBy) && Objects.equals(likedUser, favorites.likedUser) && Objects.equals(users, favorites.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, likedBy, likedUser, users);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", likedBy=" + likedBy +
                ", likedUser=" + likedUser +
                ", users=" + users +
                '}';
    }
}
