package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.models.Task;
import br.ufpb.dcx.dsc.todolist.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/tasks")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = "/{userId}")
    public List<Task> getTasks(@PathVariable Long userId){
        return todoService.listTasks(userId);
    }

    @GetMapping()
    public List<Task> getAllTasks(){
        return todoService.listTasks();
    }
}
