package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    // Método que usa a primeira query: findTasksByOwnerId
    public List<Task> getTasksByOwnerId(Long userId) {
        return taskRepository.findTasksByOwnerId(userId);
    }
    
    // Método que usa a segunda query: findTasksByOwnerIdAndSharedBoards
    public List<Task> getAllUserTasks(Long userId) {
        return taskRepository.findTasksByOwnerIdAndSharedBoards(userId);
    }
    
    // Métodos CRUD básicos
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = getTaskById(id);
        existingTask.setName(taskDetails.getName());
        existingTask.setDeadline(taskDetails.getDeadline());
        existingTask.setBoard(taskDetails.getBoard());
        return taskRepository.save(existingTask);
    }
    
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
