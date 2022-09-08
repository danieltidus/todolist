package br.ufpb.dcx.dsc.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TaskDTO {

    private String nome;
    private Long userId;
    private Long id;


    private LocalDate deadline;

    public TaskDTO(String nome, Long userId, LocalDate deadline, Long id) {
        this.nome = nome;
        this.userId = userId;
        this.deadline = deadline;
        this.id = id;
    }

    public TaskDTO() {
    }

    public TaskDTO(String nome, Long userId, String deadline) {
        this.nome = nome;
        this.userId = userId;
        // this.deadline = deadline;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "nome='" + nome + '\'' +
                ", userId=" + userId +
                ", deadline=" + deadline +
                '}';
    }
}
