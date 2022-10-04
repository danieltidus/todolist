package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    BoardRepository boardRepository;
    UserRepository userRepository;

    public BoardService(BoardRepository boardRepository,  UserRepository userRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }
    public List<Board> listBoards() {
        return boardRepository.findAll();
    }

    public Board getBoard(Long boardId) {
        return boardRepository.getReferenceById(boardId);
    }

    public Board createBoard(Board b, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            b.setUser(userOpt.get());
            return  boardRepository.save(b);
        }
        return null;
    }

    public void deleteBoard(Long boardId) {
        Optional<Board> bOpt = boardRepository.findById(boardId);
        if(bOpt.isPresent()){
            Board b = bOpt.get();
            Collection<User> users = b.getUsers();
            users.stream().forEach(user -> {
               user.getBoardsShared().remove(b);
               userRepository.save(user);
            });
            boardRepository.save(b);
        }
        boardRepository.deleteById(boardId);
    }

    public Board updateBoard(Long boardId, Board u) {
        Optional<Board> boardData = boardRepository.findById(boardId);
        if(boardData.isPresent()){
            Board toUpdate = boardData.get();
            toUpdate.setNome(u.getNome());
            return boardRepository.save(toUpdate);
        }
        return null;
    }
}
