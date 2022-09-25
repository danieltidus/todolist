package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {


    private TaskRepository taskRepository;

    TodoService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public Task getTask(Long taskId){
        return taskRepository.getReferenceById(taskId);
    }

    public List<Task> listTasks(Long userId){
        if(userId != null){
            return taskRepository.findAllByUserId(userId);
        }
        return taskRepository.findAll();
    }

    public Task saveTask(Task t) {
        return taskRepository.save(t);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long id, Task t) {
        Optional<Task> taskData = taskRepository.findById(id);
        if(taskData.isPresent()){
            Task toUpdate = taskData.get();
            toUpdate.setDeadline(t.getDeadline());
            toUpdate.setNome(t.getNome());
            taskRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

}
