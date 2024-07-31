package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.mappers.TaskMapper;
import br.ufpb.dcx.dsc.todolist.models.Task;
import br.ufpb.dcx.dsc.todolist.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class TodoController {

    // Descomente quando for usar modalMapper como exemplo
    // private final ModelMapper modelMapper;
    private final TodoService todoService;
    private final TaskMapper taskMapper;

    public TodoController(TodoService todoService, TaskMapper taskMapper) {
        this.todoService = todoService;
        this.taskMapper = taskMapper;
    }


    @GetMapping(path = "/tasks/{taskId}")
    public TaskDTO getTask(@PathVariable Long taskId){
        Task t =  todoService.getTask(taskId);
        return taskMapper.toDto(t);
    }

    // Exemplo de rota para listagem de todas as tarefas sem query string
//    @GetMapping("/tasks")
//    public List<TaskDTO> getFilteredTasks(){
//        List<Task> tasks = todoService.listTasks();
//        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
//    }

    // Exemplo com Query strings via ResquestParam - comente o de cima caso queira usar esta rota.
    @GetMapping("/tasks")
    public List<TaskDTO> getFilteredTasks(@RequestParam(name="user", required = false) Long userId){
        List<Task> tasks = todoService.listTasks(userId);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO){
        Task taskCreated = todoService.createTask(taskMapper.toEntity(taskDTO));
        return taskMapper.toDto(taskCreated);
    }

    @PutMapping("/tasks/{taskId}")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO){
        Task taskUpdated = todoService.updateTask(taskId, taskMapper.toEntity(taskDTO));
        return taskMapper.toDto(taskUpdated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        todoService.deleteTask(taskId);
    }

}
