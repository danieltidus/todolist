package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import br.ufpb.dcx.dsc.todolist.model.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Método que usa a NamedQuery findByEmail
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

     public User getUser(Long id) {
         return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
     }

     public User updateUser(Long id, User user) {
         User existing = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
         existing.setName(user.getName());
         existing.setEmail(user.getEmail());
         return userRepository.save(existing);
     }

     public void deleteUser(Long id) {
         userRepository.findById(id).ifPresent(u -> userRepository.deleteById(u.getUserId()));
     }

     public User updateUserPhoto(Long id, String photoUrl) {
         if (photoUrl == null) {
             throw new IllegalArgumentException("photoUrl must be provided");
         }
         User existing = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
         if (existing.getPhoto() == null) {
             Photo p = new Photo();
             p.setURL(photoUrl);
             existing.setPhoto(p);
         } else {
             existing.getPhoto().setURL(photoUrl);
         }
         return userRepository.save(existing);
     }
}
