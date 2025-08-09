package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class TodoService {

    private final TaskRepository taskRepository;

    TodoService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Item not found"));
    }

    public List<Task> listTasks(Long userId){
        if (userId == null) {
            return taskRepository.findAll();
        }
        // Return tasks from both owned and shared boards of the given user
        return taskRepository.findTasksByOwnerIdAndSharedBoards(userId);
    }

    public Task saveTask(Task t) {
        return taskRepository.save(t);
    }

    public void deleteTask(Long taskId) {
        taskRepository.findById(taskId)
                .ifPresent((task) -> taskRepository.deleteById(task.getId()));
    }

    public Task updateTask(Long id, Task t) {

        Optional<Task> taskData = taskRepository.findById(id);
        Task toUpdate = taskData.orElseThrow(NoSuchElementException::new);
        toUpdate.setDeadline(t.getDeadline());
        toUpdate.setName(t.getName());
        taskRepository.save(toUpdate);
        return toUpdate;

    }
}
