package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {
    
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }
    
    // Listar boards de um usuário
    public List<Board> getBoardsByUserId(Long userId) {
        User user = getUserById(userId);
        return boardRepository.findByOwner(user);
    }
    
    // Obter board específico (validando se pertence ao usuário)
    public Board getBoardByUserIdAndBoardId(Long userId, Long boardId) {
        Board board = getBoardById(boardId);
        validateBoardOwnership(board, userId);
        return board;
    }
    
    // Criar board para um usuário
    public Board createBoard(Long userId, Board board) {
        User user = getUserById(userId);
        board.setOwner(user);
        return boardRepository.save(board);
    }
    
    // Atualizar board (validando propriedade)
    public Board updateBoard(Long userId, Long boardId, Board boardDetails) {
        Board existingBoard = getBoardByUserIdAndBoardId(userId, boardId);
        existingBoard.setName(boardDetails.getName());
        return boardRepository.save(existingBoard);
    }
    
    // Deletar board (validando propriedade)
    public void deleteBoard(Long userId, Long boardId) {
        Board board = getBoardByUserIdAndBoardId(userId, boardId);
        boardRepository.delete(board);
    }
    
    // Métodos auxiliares
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
    }
    
    private Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board not found with id: " + boardId));
    }
    
    private void validateBoardOwnership(Board board, Long userId) {
        if (!board.getOwner().getUserId().equals(userId)) {
            throw new IllegalArgumentException("Board does not belong to user with id: " + userId);
        }
    }
}
