package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class TodoController {

    private final ModelMapper modelMapper;
    private final TodoService todoService;


    public TodoController(TodoService todoService, ModelMapper
            modelMapper) {
        this.todoService = todoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/task/{taskId}")
    public TaskDTO getTask(@PathVariable Long taskId){
        Task t =  todoService.getTask(taskId);
        return convertToDTO(t);
    }

    @GetMapping("/task")
    public List<TaskDTO> getFilteredTasks(){
        List<Task> tasks = todoService.listTasks();
        return tasks.stream().map(task -> convertToDTO(task)).collect(Collectors.toList());
    }

    @PostMapping("/board/{boardId}/task")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO, @PathVariable Long boardId){
        Task t = convertToEntity(taskDTO);
        Task taskCreated = todoService.saveTask(boardId, t);
        return convertToDTO(taskCreated);
    }

    @PutMapping("/task/{taskId}")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO){
        Task t = convertToEntity(taskDTO);
        Task taskUpdated = todoService.updateTask(taskId, t);
        return convertToDTO(taskUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/task/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        todoService.deleteTask(taskId);
    }

    private TaskDTO convertToDTO(Task t) {
        return modelMapper.map(t, TaskDTO.class);
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
