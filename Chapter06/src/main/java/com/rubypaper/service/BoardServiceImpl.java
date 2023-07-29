package com.rubypaper.service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepo;

    public List<Board> getBoardList(Board board){
        return (List<Board>) boardRepo.findAll();
    }

    public void insertBoard(Board board){
        boardRepo.save(board);
    }

    public Board getBoard(Board board){
        return null;
    }

    public void updateBoard(Board board){
    }

    public void deleteBoard(Board board){
    }
}
