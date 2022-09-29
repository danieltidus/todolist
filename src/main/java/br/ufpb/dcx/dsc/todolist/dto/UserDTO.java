package br.ufpb.dcx.dsc.todolist.dto;

import br.ufpb.dcx.dsc.todolist.model.Photo;
import br.ufpb.dcx.dsc.todolist.model.User;

public class UserDTO {

    private Long id;
    private String nome;
    private String email;

    private Photo photo;

    public UserDTO(){}

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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", photo=" + photo +
                '}';
    }
}
