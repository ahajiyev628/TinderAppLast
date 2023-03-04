package com.example.registrationlogindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    private String status; // This can be either "like" or "dislike"

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Favorites() {

    }
    public Favorites(Set<User> users, String status) {
        this.status = status;
        this.users = users;
    }

    public Favorites(Optional<User> user, String status) {
        this.status = status;
        this.users = users;
    }
}


