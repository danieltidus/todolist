package br.ufpb.dcx.dsc.todolist.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "nome")
    private String nome;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(name = "compartilhamento",
    joinColumns = @JoinColumn(name = "board_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    public Board(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", user=" + user +
                ", users=" + users +
                '}';
    }
}
