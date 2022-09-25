package br.ufpb.dcx.dsc.todolist.repository;

import br.ufpb.dcx.dsc.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);
}
