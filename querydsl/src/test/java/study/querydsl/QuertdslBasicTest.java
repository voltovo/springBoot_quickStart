package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class QuertdslBasicTest {
    @Autowired
    EntityManager em;

    @BeforeEach
    public void before(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }
    
    @Test
    public void startJPQL() throws Exception{
      //given
        //when
        String queryString = "select m from Member m where m.username = :username";
        Member findMember = em.createQuery(queryString, Member.class)
            .setParameter("username", "member1")
            .getSingleResult();

      //then
        assertThat(findMember.getUsername()).isEqualTo("member1");
     }
     
     @Test
     public void startQuerydsl() throws Exception{
       //given
         JPAQueryFactory queryFactory = new JPAQueryFactory(em);
         QMember m = QMember.member;
         //when
         Member findMember = queryFactory.selectFrom(m).where(m.username.eq("member1")).fetchOne();

         //then
         assertThat(findMember.getUsername()).isEqualTo("member1");
      
      }
      
      @Test
      public void findDtoByJPQL() throws Exception{
        //given
          List<MemberDto> result = em.createQuery(
              "select " + "new study.querydsl.dto.MemberDto(m.username, m.age)" + " from Member m",
              MemberDto.class).getResultList();
          //when
          for (MemberDto memberDto : result) {
              System.out.println("memberDto = " + memberDto);
          }
        //then
       
       }
}