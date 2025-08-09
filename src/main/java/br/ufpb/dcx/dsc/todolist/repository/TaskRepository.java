package br.ufpb.dcx.dsc.todolist.repository;

import br.ufpb.dcx.dsc.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Optimized query to find all tasks owned by a user (through board ownership)
    @Query("SELECT t FROM Task t JOIN FETCH t.board b WHERE b.owner.id = :userId")
    List<Task> findTasksByOwnerId(@Param("userId") Long userId);

    // Find tasks from both owned and shared boards for a given user
    @Query("SELECT DISTINCT t FROM Task t JOIN FETCH t.board b WHERE b.owner.id = :userId " +
           "OR b IN (SELECT sb FROM User u JOIN u.sharedBoards sb WHERE u.id = :userId)")
    List<Task> findTasksByOwnerIdAndSharedBoards(@Param("userId") Long userId);
}
