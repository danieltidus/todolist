package br.ufpb.dcx.dsc.todolist.dto;

import br.ufpb.dcx.dsc.todolist.model.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardDTO {

    private Long id;
    private String nome;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", user=" + user +
                '}';
    }
}
