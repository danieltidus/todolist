package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            boardRepository.save(b);
        }
        return null;
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
