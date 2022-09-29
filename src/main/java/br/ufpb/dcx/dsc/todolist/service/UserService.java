package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User u) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setEmail(u.getEmail());
            user.setNome(u.getNome());
            userRepository.save(user);
        }
        return null;
    }
    
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
