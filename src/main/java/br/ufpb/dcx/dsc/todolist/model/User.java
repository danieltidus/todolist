package br.ufpb.dcx.dsc.todolist.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    // Boards owned by this user (one-to-many)
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Board> ownedBoards;

    // Boards shared with this user (many-to-many)
    @ManyToMany(mappedBy = "sharedWithUsers", fetch = FetchType.LAZY)
    private Collection<Board> sharedBoards;


    /*INFO: We use cascade = CascadeType.ALL and orphanRemoval = true to ensure that the photo is deleted if the user
    is deleted and to ensure that the photo is updated if the user is updated. */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public User() {
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Board> getOwnedBoards() {
        return ownedBoards;
    }

    public void setOwnedBoards(Collection<Board> ownedBoards) {
        this.ownedBoards = ownedBoards;
    }

    public Collection<Board> getSharedBoards() {
        return sharedBoards;
    }

    public void setSharedBoards(Collection<Board> sharedBoards) {
        this.sharedBoards = sharedBoards;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
