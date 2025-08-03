package br.ufpb.dcx.dsc.todolist.model;

import java.time.LocalDate;

//@Entity
//@Table(name = "tb_tasks")
public class Task {

 //   @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    final private Long id;

//    @Column(name = "nome")
    private String nome;

  //  @Column(name = "user_id")
    private Long userId;

//    @Column(name = "deadline")
    private LocalDate deadline;

    // FIXME: We no longer need this sequential generator
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

    // For ID, we don't have a setter since it's generated automatically
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
