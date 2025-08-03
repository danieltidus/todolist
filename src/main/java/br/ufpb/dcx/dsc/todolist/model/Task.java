package br.ufpb.dcx.dsc.todolist.model;

import java.time.LocalDate;

@Entity
@Table(name = "tb_tasks")
public class Task {

 //   @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    final private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deadline")
    private LocalDate deadline;

    // FIXME: We no longer need this sequential generator
    private static Long IdSequenceCounter = 0L;

    public Task(){
        this.id = Task.IdSequenceCounter++;
    }

    public Task(Long userId,String name, LocalDate deadline){
        this.userId = userId;
        this.name = name;
        this.deadline = deadline;
        this.id = Task.IdSequenceCounter++;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "nome='" + name + '\'' +
                ", userId=" + userId +
                ", deadline=" + deadline +
                ", id=" + id +
                '}';
    }
}
