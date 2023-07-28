package com.rubypaper.persistence;

import com.rubypaper.domain.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>{
    List<Board> findByTitle(String searchKeyword);
    List<Board> findByContentContaining(String searchKeyword);
}
