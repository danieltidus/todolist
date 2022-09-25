package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    List<User> listUsers(){
        return userService.listUsers();
    }
    @PostMapping(path = "/users")
    User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
