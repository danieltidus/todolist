package br.ufpb.dcx.dsc.todolist.models;

import java.time.LocalDateTime;

public class Task {

    private String nome;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private LocalDateTime deadline;
    private Long id;
    private static Long IdSequenceCounter = 0L;

    public Task(Long userId,String nome, LocalDateTime deadline){
        this.userId = userId;
        this.nome = nome;
        this.deadline = deadline;
        this.id = Task.IdSequenceCounter++;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

}
