package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.UserDTO;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Validated
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public  UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/users")
    List<User> listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUser(@PathVariable @Min(4) Long userId){
        User user = userService.getUser(userId);
        System.out.println(user.toString());
        return convertToDTO(user);
    }


    @PostMapping(path = "/users")
    UserDTO createUser(@Valid @RequestBody UserDTO userDTO){
        User u = convertToEntity(userDTO);
        User saved = userService.createUser(u);
        return convertToDTO(saved);
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateTask(@PathVariable Long userId, @RequestBody UserDTO userDTO){

        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/boards/{boardId}/users/{userId}/share")
    public UserDTO shareBoard(@PathVariable Long userId, @PathVariable Long boardId){
        User u = userService.share(boardId, userId);
        return convertToDTO(u);
    }

    @DeleteMapping("/boards/{boardId}/users/{userId}/share")
    public UserDTO unshareBoard(@PathVariable Long userId, @PathVariable Long boardId){
        User u = userService.unshare(boardId, userId);
        return convertToDTO(u);
    }

    private UserDTO convertToDTO(User u) {
        return modelMapper.map(u, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
