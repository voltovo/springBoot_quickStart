package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class QuertdslBasicTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
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
         QMember m = member;
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

       @Test
       public void findDtoBySetter() throws Exception{
         //given
           List<MemberDto> result = queryFactory.select(
              Projections.bean(MemberDto.class, member.username, member.age)).from(member).fetch();
           //when
           for (MemberDto memberDto : result) {
               System.out.println("memberDto = " + memberDto);
           }
         //then

        }

    @Test
    public void findDtoByField() throws Exception {
        //given
        List<MemberDto> result = queryFactory.select(
            Projections.fields(MemberDto.class, member.username, member.age)).from(member).fetch();
        //when
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
        //then

    }

    @Test
    public void findDtoByConstructor() throws Exception {
        //given
        List<MemberDto> result = queryFactory.select(
            Projections.constructor(MemberDto.class, member.username, member.age)).from(member).fetch();
        //when
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
        //then

    }

    @Test
    public void dynamicQuery_BooleanBuilder() throws Exception{
      //given
      String usernameParam = "member1";
      Integer ageParam = 10;
      //when

        List<Member> result = searchMember1(usernameParam, ageParam);

      //then
        assertThat(result.size()).isEqualTo(1);
     }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }
        return queryFactory.selectFrom(member).where(builder).fetch();
    }
}

