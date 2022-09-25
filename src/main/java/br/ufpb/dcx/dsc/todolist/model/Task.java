package br.ufpb.dcx.dsc.todolist.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deadline")
    private LocalDate deadline;

    public Task(){
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

    // Para o ID não temos set já que ele é gerado automaticamente
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
