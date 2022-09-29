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

    @OneToMany(mappedBy = "user")
    private Collection<Board> boards;

    @ManyToMany(mappedBy = "users")
    private Collection<Board> boardsShared;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "user")
    private Collection<Task> tasks;
    public User() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Board> getBoards() {
        return boards;
    }

    public void setBoards(Collection<Board> boards) {
        this.boards = boards;
    }

    public Collection<Board> getBoardsShared() {
        return boardsShared;
    }

    public void setBoardsShared(Collection<Board> boardsShared) {
        this.boardsShared = boardsShared;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }


}
