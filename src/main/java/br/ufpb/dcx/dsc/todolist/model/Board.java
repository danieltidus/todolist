package br.ufpb.dcx.dsc.todolist.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "nome")
    private String nome;


    @ManyToMany()
    @JoinTable(name = "compartilhamento",
    joinColumns = @JoinColumn(name = "board_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    public Board(){}
}
