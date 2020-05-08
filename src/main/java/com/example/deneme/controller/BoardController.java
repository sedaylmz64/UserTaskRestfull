package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateBoardRequest;
import com.example.deneme.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public void createBoard(@Valid @RequestBody CreateBoardRequest request){
        boardService.createBoard(request);
    }
}
