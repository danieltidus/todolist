package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.PhotoDTO;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.service.UserService;
import br.ufpb.dcx.dsc.todolist.dto.UserDTO;
import br.ufpb.dcx.dsc.todolist.dto.UserCreateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import br.ufpb.dcx.dsc.todolist.model.Photo;

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
    List<UserDTO> listUsers(@RequestParam(required = false) String email){
        if (email != null && !email.isEmpty()) {
            // Se email foi fornecido, busca por email usando NamedQuery
            User user = userService.getUserByEmail(email);
            return List.of(convertToDTO(user));
        }
        // Se não, lista todos os usuários
        return userService.listUsers()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{id}")
    UserDTO getUser(@PathVariable Long id) {
        return convertToDTO(userService.getUser(id));
    }

    @PostMapping(path = "/users")
    UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO){
        User user = convertToEntity(userCreateDTO);
        User created = userService.createUser(user);
        return convertToDTO(created);
    }

    @PutMapping(path = "/users/{id}")
    UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User toUpdate = convertToEntity(userDTO);
        User updated = userService.updateUser(id, toUpdate);
        return convertToDTO(updated);
    }

    @PatchMapping(path = "/users/{id}/photo")
    UserDTO updateUserPhoto(@PathVariable Long id, @RequestBody PhotoDTO photo) {
        User updated = userService.updateUserPhoto(id, photo.getUrl());
        return convertToDTO(updated);
    }

    @DeleteMapping(path = "/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        // Manually map nested Photo URL to flat DTO field

        if (user.getPhoto() != null) {
            dto.setPhotoUrl(user.getPhoto().getURL());
            System.out.println(user.getPhoto().getURL());
        }
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private User convertToEntity(UserCreateDTO dto) {
        return modelMapper.map(dto, User.class);
    }

}
