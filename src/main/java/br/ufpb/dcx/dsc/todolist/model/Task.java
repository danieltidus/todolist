package br.ufpb.dcx.dsc.todolist.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "deadline")
    private LocalDate deadline;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Task(){
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
        return taskId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", nome='" + nome + '\'' +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }
}
