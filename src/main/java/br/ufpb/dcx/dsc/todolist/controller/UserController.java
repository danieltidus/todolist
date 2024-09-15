package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.UserDTO;
import br.ufpb.dcx.dsc.todolist.dto.UserDTOResponse;
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
    List<UserDTOResponse> listUsers(){
        return userService.listUsers().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/users/{userId}")
    public UserDTOResponse getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        return convertToDTO(user);
    }


    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTOResponse createUser(@Valid @RequestBody UserDTO userDTO){
        User u = convertToEntity(userDTO);
        User saved = userService.createUser(u);
        return convertToDTO(saved);
    }

    @PutMapping("/users/{userId}")
    public UserDTOResponse updateTask(@PathVariable Long userId, @RequestBody UserDTO userDTO){

        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }


    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/boards/{boardId}/users/{userId}/share")
    @ResponseStatus(HttpStatus.OK)
    public UserDTOResponse shareBoard(@PathVariable Long userId, @PathVariable Long boardId){
        User u = userService.share(boardId, userId);
        return convertToDTO(u);
    }


    @DeleteMapping("/boards/{boardId}/users/{userId}/share")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDTOResponse unshareBoard(@PathVariable Long userId, @PathVariable Long boardId){
        User u = userService.unshare(boardId, userId);
        return convertToDTO(u);
    }

    private UserDTOResponse convertToDTO(User u) {
        return modelMapper.map(u, UserDTOResponse.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
