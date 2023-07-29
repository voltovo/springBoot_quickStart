package com.rubypaper.controller;

import com.rubypaper.domain.Board;
import com.rubypaper.service.BoardService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @RequestMapping("/getBoardList")
    public String getBoardList(Model model, Board board){
        List<Board> boardList = boardService.getBoardList(board);

        model.addAttribute("boardList", boardList);
        return "getBoardList";
    }
}
