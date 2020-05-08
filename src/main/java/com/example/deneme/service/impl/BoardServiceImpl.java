package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateBoardRequest;
import com.example.deneme.model.converter.CreateBoardRequestConverter;
import com.example.deneme.model.entity.BoardEntity;
import com.example.deneme.repositories.BoardRepository;
import com.example.deneme.service.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void createBoard(CreateBoardRequest request) {
        BoardEntity boardEntity = CreateBoardRequestConverter.convert(request);
        boardRepository.save(boardEntity);
    }
}
