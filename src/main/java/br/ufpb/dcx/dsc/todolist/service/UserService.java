package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import br.ufpb.dcx.dsc.todolist.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Email;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Validated
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Method that uses NamedQuery findByEmail with validation
    public User getUserByEmail(@NotBlank(message = "Email cannot be blank") 
                              @Email(message = "Email must be valid") String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User createUser(@Valid @NotNull(message = "User cannot be null") User user){
        // Business logic validation example
        validateUniqueEmail(user.getEmail(), null);
        return userRepository.save(user);
    }

     public User getUser(@NotNull(message = "ID cannot be null") 
                        @Positive(message = "ID must be positive") Long id) {
         return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
     }

     public User updateUser(@NotNull(message = "ID cannot be null") 
                           @Positive(message = "ID must be positive") Long id, 
                           @Valid @NotNull(message = "User cannot be null") User user) {
         User existing = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
         
         // Business logic validation - check if email is unique (excluding current user)
         validateUniqueEmail(user.getEmail(), id);
         
         existing.setName(user.getName());
         existing.setEmail(user.getEmail());
         return userRepository.save(existing);
     }

     public void deleteUser(@NotNull(message = "ID cannot be null") 
                           @Positive(message = "ID must be positive") Long id) {
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
     
     // Business logic validation methods
     private void validateUniqueEmail(String email, Long excludeUserId) {
         if (email == null || email.trim().isEmpty()) {
             throw new IllegalArgumentException("Email cannot be null or empty");
         }
         
         userRepository.findByEmail(email).ifPresent(existingUser -> {
             if (excludeUserId == null || !existingUser.getUserId().equals(excludeUserId)) {
                 throw new IllegalArgumentException("Email already exists: " + email);
             }
         });
     }
     

}
