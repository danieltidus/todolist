package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.exception.ItemNotFoundException;
import br.ufpb.dcx.dsc.todolist.exception.ShareBoardException;
import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.Photo;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.PhotoRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Min;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final BoardRepository boardRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository, BoardRepository boardRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.boardRepository = boardRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    public User getUser(Long userId) {
            return userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
    }

    public User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Photo photo = new Photo("www.exemplo.com/foto.png");
        photoRepository.save(photo);
        user.setPhoto(photo);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User u) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
        user.setEmail(u.getEmail());
        user.setNome(u.getNome());
        return userRepository.save(user);
    }
    
    public void deleteUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));

        // Remove all boards shared with me
        u.getBoardsShared().removeAll(u.getBoardsShared());
        // Remove users who share my boards
        Collection<Board> myBoards = u.getBoards();
        myBoards.stream().forEach(board -> {
            Collection<User> users = board.getUsers();
            users.stream().forEach(user -> {
                user.getBoardsShared().remove(board);
                userRepository.save(user);
            });
            boardRepository.save(board);
        });
        userRepository.save(u);
        userRepository.delete(u);

    }

    public User share(Long boardId, Long userId){
        User u = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
        Board b = boardRepository.findById(boardId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
        u.getBoardsShared().add(b);
        return userRepository.save(u);
    }

    public User unshare(Long boardId, Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
        Board b = boardRepository.findById(boardId).orElseThrow(() -> new ItemNotFoundException("User " + userId + " not found!"));
        u.getBoardsShared().remove(b);
        return userRepository.save(u);

    }
}