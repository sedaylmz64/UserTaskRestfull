package com.example.deneme.service;

import com.example.deneme.controller.request.CreateBoardRequest;
import org.springframework.stereotype.Component;

@Component
public interface BoardService {
    void createBoard(CreateBoardRequest request);
}
