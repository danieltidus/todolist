package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.dto.UserDTO;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService userService;
    private final ModelMapper modelMapper;

    public  UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/users")
    List<User> listUsers(){
        return userService.listUsers();
    }

    @PostMapping(path = "/users")
    User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/user/{userId}")
    public UserDTO updateTask(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    private UserDTO convertToDTO(User u) {
        return modelMapper.map(u, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
