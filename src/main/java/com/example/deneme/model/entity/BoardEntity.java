package com.example.deneme.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardEntity {

    public BoardEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "board_name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private TeamEntity teamEntity;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BoardColumnEntity> boardColumnEntityList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public List<BoardColumnEntity> getBoardColumnEntityList() {
        return boardColumnEntityList;
    }

    public void setBoardColumnEntityList(List<BoardColumnEntity> boardColumnEntityList) {
        this.boardColumnEntityList = boardColumnEntityList;
    }
}
