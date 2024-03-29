package com.study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.study.datajpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void basicCRUD() throws Exception {
        //given
        Member member1 = new Member("member1", 30);
        Member member2 = new Member("member2", 40);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        //then
        // 단건 조회
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        count = memberRepository.count();
        assertThat(count).isEqualTo(0);
    }
    
    @Test
    public void pagingTest() throws Exception{
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 4, Direction.DESC, "username");

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        //then
        List<Member> content = page.getContent();

        // 현재 페이지의 데이터 수
        assertThat(content.size()).isEqualTo(4);
        // 전체 데이터 수
        assertThat(page.getTotalElements()).isEqualTo(6);
        // 전체 페이지 수
        assertThat(page.getTotalPages()).isEqualTo(2);
        // 현재 페이지가 첫번째 인지 확인
        assertThat(page.isFirst()).isTrue();
        // 다음 페이지가 있는지 확인
        assertThat(page.hasNext()).isTrue();

     
     }

    @Test
    public void bulkUpdate() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));
        memberRepository.save(new Member("member6", 9));
        //when
        // bulkAgePlus query 실행 후 클리어 실행
        // clearAutomatically = true
        int resultCount = memberRepository.bulkAgePlus(20);

        // 영속성 컨텍스트 초기화
        // 벌크성 쿼리를 사용하면 꼭 초기화를 해야 한다
//        em.flush();
//        em.clear();행

        // 벌크 쿼리의 문제점 처리
        // 영속성 컨텍스트를 초기화 하고 난 후 조회
        Member findMember = memberRepository.findByUsername("member5").get();
        System.out.println("findMember = " + findMember);
        //then
        assertThat(resultCount).isEqualTo(3);
        // DB의 데이터는 41, 영속성 컨텍스트의 데이터는 40
        assertThat(findMember.getAge()).isEqualTo(41);
    }
}
