package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.Photo;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.PhotoRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserService {
    private UserRepository userRepository;
    private PhotoRepository photoRepository;
    private BoardRepository boardRepository;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository, BoardRepository boardRepository){
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.boardRepository = boardRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    public User getUser(@Min(4) Long userId) {

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
        Optional<User> uOpt = userRepository.findById(userId);
        User u = uOpt.get();
        if(uOpt.isPresent()){
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

    }

    public User share(Long boardId, Long userId){
        Optional<User> uOpt = userRepository.findById(userId);
        Optional<Board> bOpt = boardRepository.findById(boardId);

        if(uOpt.isPresent() && bOpt.isPresent()){
            User u = uOpt.get();
            u.getBoardsShared().add(bOpt.get());
            return userRepository.save(u);
        }

        return null;
    }

    public User unshare(Long boardId, Long userId) {
        Optional<User> uOpt = userRepository.findById(userId);
        Optional<Board> bOpt = boardRepository.findById(boardId);

        if(uOpt.isPresent() && bOpt.isPresent()){
            User u = uOpt.get();
            u.getBoardsShared().remove(bOpt.get());
            return userRepository.save(u);
        }
        return null;
    }
}