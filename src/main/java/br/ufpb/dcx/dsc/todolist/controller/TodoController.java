package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class TodoController {

    // Descomente quando for usar modalMapper como exemplo
    private final ModelMapper modelMapper;
    private final TodoService todoService;


    // Descomente quando for usar modalMapper como exemplo
    public TodoController(TodoService todoService, ModelMapper
            modelMapper) {
        this.todoService = todoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/tasks/{taskId}")
    public TaskDTO getTask(@PathVariable Long taskId){
        Task t =  todoService.getTask(taskId);
        return convertToDTO(t);
    }

    // Exemplo com Query strings via ResquestParam - comente o de cima caso queira usar esta rota.
    @GetMapping("/tasks")
    public List<TaskDTO> getFilteredTasks(@RequestParam(name="user", required = false) Long userId){
        System.out.println("userId " + userId);
        List<Task> tasks = todoService.listTasks(userId);
        return tasks.stream().map(task -> convertToDTO(task)).collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO){
        Task t = convertToEntity(taskDTO);
        Task taskCreated = todoService.saveTask(t);
        return convertToDTO(taskCreated);
    }

    @PutMapping("/tasks/{taskId}")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO){
        Task t = convertToEntity(taskDTO);
        Task taskUpdated = todoService.updateTask(taskId, t);
        return convertToDTO(taskUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        todoService.deleteTask(taskId);
    }

    // Exemplo de conversão com Modal Mapper
    // Descomente quando for usar modalMapper como exemplo
    private TaskDTO convertToDTO(Task t) {
        return modelMapper.map(t, TaskDTO.class);
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
