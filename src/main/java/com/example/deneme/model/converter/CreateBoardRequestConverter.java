package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateBoardRequest;
import com.example.deneme.model.entity.BoardEntity;

public class CreateBoardRequestConverter {
    public static BoardEntity convert(CreateBoardRequest request){
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setName(request.getName());
        boardEntity.setDescription(request.getDescription());

        return boardEntity;
    }
}
