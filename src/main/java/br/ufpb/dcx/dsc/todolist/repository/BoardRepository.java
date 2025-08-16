package br.ufpb.dcx.dsc.todolist.repository;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
    // Buscar boards por proprietário
    List<Board> findByOwner(User owner);
    
    // Buscar boards por proprietário ID
    List<Board> findByOwnerId(Long ownerId);
}
