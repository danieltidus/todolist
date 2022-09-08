package br.ufpb.dcx.dsc.todolist.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private String nome;
    private Long userId;
    private LocalDate deadline;
    private final Long id;
    private static Long IdSequenceCounter = 0L;

    public Task(){
        this.id = Task.IdSequenceCounter++;
    }

    public Task(Long userId,String nome, LocalDate deadline){
        this.userId = userId;
        this.nome = nome;
        this.deadline = deadline;
        this.id = Task.IdSequenceCounter++;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Task{" +
                "nome='" + nome + '\'' +
                ", userId=" + userId +
                ", deadline=" + deadline +
                ", id=" + id +
                '}';
    }
}
