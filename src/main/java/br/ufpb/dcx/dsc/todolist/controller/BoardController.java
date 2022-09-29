package br.ufpb.dcx.dsc.todolist.controller;

import br.ufpb.dcx.dsc.todolist.dto.BoardDTO;
import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class BoardController {

    private ModelMapper modelMapper;
    private BoardService boardService;

    public BoardController(BoardService boardService, ModelMapper modelMapper){
        this.boardService = boardService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(path = "/board")
    List<Board> listBoards(){
        return boardService.listBoards();
    }

    @GetMapping("/board/{boardId}")
    public BoardDTO getBoard(@PathVariable Long boardId){
        Board board = boardService.getBoard(boardId);
        return convertToDTO(board);
    }


    @PostMapping(path = "/user/{userId}/board")
    BoardDTO createBoard(@RequestBody BoardDTO boardDTO, @PathVariable Long userId){
        Board b = convertToEntity(boardDTO);
        Board saved = boardService.createBoard(b, userId);
        return convertToDTO(saved);
    }

//    @PutMapping("/board/{boardId}")
//    public BoardDTO updateTask(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO){
//
//        Board u = convertToEntity(boardDTO);
//        Board boardUpdated = boardService.updateBoard(boardId, u);
//        return convertToDTO(boardUpdated);
//    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
    }

    private BoardDTO convertToDTO(Board u) {
        return modelMapper.map(u, BoardDTO.class);
    }

    private Board convertToEntity(BoardDTO boardDTO) {
        return modelMapper.map(boardDTO, Board.class);
    }
}
