package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Photo;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.PhotoRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PhotoRepository photoRepository;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository){
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    public User getUser(Long userId) {

        if(userId != null)
            return userRepository.getReferenceById(userId);
        return null;
    }

    public User createUser(User user){

        Photo photo = new Photo("www.exemplo.com/foto.png");
        photoRepository.save(photo);
        user.setPhoto(photo);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User u) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setEmail(u.getEmail());
            user.setNome(u.getNome());
            return userRepository.save(user);
        }
        return null;
    }
    
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
