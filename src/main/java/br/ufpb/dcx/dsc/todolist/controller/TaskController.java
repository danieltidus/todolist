package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class    TaskController {
    
    private final TaskService taskService;
    private final ModelMapper modelMapper;
    
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }
    
    // Rota que usa o primeiro método: findTasksByOwnerId
    @GetMapping(path = "/tasks/owner/{userId}")
    public List<TaskDTO> getTasksByOwnerId(@PathVariable Long userId) {
        return taskService.getTasksByOwnerId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Rota que usa o segundo método: findTasksByOwnerIdAndSharedBoards
    @GetMapping(path = "/tasks/user/{userId}")
    public List<TaskDTO> getAllUserTasks(@PathVariable Long userId) {
        return taskService.getAllUserTasks(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/tasks")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/tasks/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return convertToDTO(taskService.getTaskById(id));
    }
    
    @PostMapping(path = "/tasks")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task createdTask = taskService.createTask(task);
        return convertToDTO(createdTask);
    }
    
    @PutMapping(path = "/tasks/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task taskDetails = convertToEntity(taskDTO);
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return convertToDTO(updatedTask);
    }
    
    @DeleteMapping(path = "/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    
    // Métodos de conversão
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = modelMapper.map(task, TaskDTO.class);
        // Se a task tem um board e o board tem um owner, pega o userId
        if (task.getBoard() != null && task.getBoard().getOwner() != null) {
            dto.setUserId(task.getBoard().getOwner().getUserId());
        }
        return dto;
    }
    
    private Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
