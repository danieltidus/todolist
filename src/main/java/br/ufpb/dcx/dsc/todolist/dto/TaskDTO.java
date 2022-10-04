package br.ufpb.dcx.dsc.todolist.dto;

import br.ufpb.dcx.dsc.todolist.model.Board;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TaskDTO {

    private String nome;
    private Long id;
    private LocalDate deadline;
    private Board board;

    public TaskDTO() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", deadline=" + deadline +
                ", board=" + board +
                '}';
    }
}
