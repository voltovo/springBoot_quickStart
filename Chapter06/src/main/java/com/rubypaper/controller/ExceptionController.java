package com.rubypaper.controller;

import com.rubypaper.exception.BoardException;
import com.rubypaper.exception.BoardNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class ExceptionController {
    @RequestMapping("/boardError")
    public String boardError(){
        throw new BoardNotFoundException("검색된 게시글이 없습니다.");
    }

    @RequestMapping("/illegalArgumentError")
    public String ellegalArgumentError(){
        throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
    }

    @RequestMapping("/sqlError")
    public String sqlError() throws SQLException {
        throw new SQLException("SQL 구문에 오류가 있습니다.");
    }
}
