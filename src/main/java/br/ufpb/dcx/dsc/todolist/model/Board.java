package br.ufpb.dcx.dsc.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tb_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "name")
    private String name;

    // Owner relationship - each board belongs to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnoreProperties("ownedBoards")
    private User owner;

    // Sharing relationship - a board can be shared with many users
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "compartilhamento",
    joinColumns = @JoinColumn(name = "board_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("sharedBoards")
    private Collection<User> sharedWithUsers;

    // Tasks relationship - a board can have many tasks
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Task> tasks;

    public Board(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Collection<User> getSharedWithUsers() {
        return sharedWithUsers;
    }

    public void setSharedWithUsers(Collection<User> sharedWithUsers) {
        this.sharedWithUsers = sharedWithUsers;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + (owner != null ? owner.getName() : "null") +
                '}';
    }
}
