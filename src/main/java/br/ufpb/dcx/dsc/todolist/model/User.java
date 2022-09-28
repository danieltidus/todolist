package br.ufpb.dcx.dsc.todolist.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "users")
    private Collection<Board> boards;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "user")
    private Collection<Task> tasks;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
