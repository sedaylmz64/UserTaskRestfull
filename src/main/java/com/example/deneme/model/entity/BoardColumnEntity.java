package com.example.deneme.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "boardColumn")
public class BoardColumnEntity {

    public BoardColumnEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "board_column_name")
    private String boardColumnName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private BoardEntity boardEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoardColumnName() {
        return boardColumnName;
    }

    public void setBoardColumnName(String boardColumnName) {
        this.boardColumnName = boardColumnName;
    }

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }
}
