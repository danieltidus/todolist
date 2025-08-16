package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.BoardDTO;
import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/users/{userId}/boards")
public class BoardController {
    
    private final BoardService boardService;
    private final ModelMapper modelMapper;
    
    public BoardController(BoardService boardService, ModelMapper modelMapper) {
        this.boardService = boardService;
        this.modelMapper = modelMapper;
    }
    
    // GET /api/users/{userId}/boards - Listar boards do usuário
    @GetMapping
    public List<BoardDTO> getBoardsByUserId(@PathVariable Long userId) {
        return boardService.getBoardsByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // GET /api/users/{userId}/boards/{boardId} - Obter board específico
    @GetMapping("/{boardId}")
    public BoardDTO getBoardByUserIdAndBoardId(@PathVariable Long userId, @PathVariable Long boardId) {
        Board board = boardService.getBoardByUserIdAndBoardId(userId, boardId);
        return convertToDTO(board);
    }
    
    // POST /api/users/{userId}/boards - Criar board para o usuário
    @PostMapping
    public BoardDTO createBoard(@PathVariable Long userId, @RequestBody BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        Board createdBoard = boardService.createBoard(userId, board);
        return convertToDTO(createdBoard);
    }
    
    // PUT /api/users/{userId}/boards/{boardId} - Atualizar board
    @PutMapping("/{boardId}")
    public BoardDTO updateBoard(@PathVariable Long userId, @PathVariable Long boardId, @RequestBody BoardDTO boardDTO) {
        Board boardDetails = convertToEntity(boardDTO);
        Board updatedBoard = boardService.updateBoard(userId, boardId, boardDetails);
        return convertToDTO(updatedBoard);
    }
    
    // DELETE /api/users/{userId}/boards/{boardId} - Deletar board
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long userId, @PathVariable Long boardId) {
        boardService.deleteBoard(userId, boardId);
    }
    
    // Métodos de conversão
    private BoardDTO convertToDTO(Board board) {
        BoardDTO dto = modelMapper.map(board, BoardDTO.class);
        dto.setOwnerId(board.getOwner().getUserId());
        return dto;
    }
    
    private Board convertToEntity(BoardDTO boardDTO) {
        return modelMapper.map(boardDTO, Board.class);
    }
}
