package com.rubypaper.persistence;

import com.rubypaper.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    // 글 제목 검색하기
    List<Board> findByTitle(String searchKeyword);
    // 글 제목 LIKE 검색하기
    List<Board> findByContentContaining(String searchKeyword);
    // 글 제목 또는 글 내용 LIKE 검색하기
    List<Board> findByTitleContainingOrContentContaining(String title, String content);
    // 조회 데이터 정렬하기
    List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
    // 페이징 처리하기
    Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
    // 네이티브 쿼리 사용
//    @Query(value = "select seq, title, writer, create_date from board where title like '%'||?1||'%' order by seq desc", nativeQuery = true)
//    List<Object[]> queryAnnotationTest(String searchKeyword);
}
