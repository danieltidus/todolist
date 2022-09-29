package br.ufpb.dcx.dsc.todolist.repository;

import br.ufpb.dcx.dsc.todolist.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
