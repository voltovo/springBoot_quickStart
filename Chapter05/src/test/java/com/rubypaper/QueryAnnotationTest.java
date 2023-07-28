package com.rubypaper;

import com.rubypaper.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class QueryAnnotationTest {
    @Autowired
    private BoardRepository boardRepo;

//    @Test
//    public void testQueryAnnotationTest(){
//        List<Object[]> boardList = boardRepo.queryAnnotationTest("테스트 제목 10");
//        System.out.println("검색 결과");
//        for(Object[] row : boardList){
//            System.out.println("---> " + Arrays.toString(row));
//        }
//    }
}
