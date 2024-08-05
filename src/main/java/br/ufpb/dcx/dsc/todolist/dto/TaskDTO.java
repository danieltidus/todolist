package br.ufpb.dcx.dsc.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TaskDTO {

    private String name;
    private Long userId;
    private Long id;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deadline;

    public TaskDTO(String nome, Long userId, LocalDate deadline, Long id) {
        this.name = nome;
        this.userId = userId;
        this.deadline = deadline;
        this.id = id;
    }

    public TaskDTO() {
    }

    public TaskDTO(String nome, Long userId, String deadline) {
        this.name = nome;
        this.userId = userId;
        // this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "nome='" + name + '\'' +
                ", userId=" + userId +
                ", deadline=" + deadline +
                '}';
    }
}
