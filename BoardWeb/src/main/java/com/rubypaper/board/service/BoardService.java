package com.rubypaper.board.service;

import com.rubypaper.board.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardService {
    void insertBoard(Board board);
    void updateBoard(Board borard);
    void deleteBoard(Board board);
    Board getBoard(Board board);
    Page<Board> getBoardList(Board board);
}
