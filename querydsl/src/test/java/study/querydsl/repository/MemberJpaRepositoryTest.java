package study.querydsl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception{
      //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);
        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();

        List<Member> findMember1 = memberJpaRepository.findAll_Querydsl();

        List<Member> findMember2 = memberJpaRepository.findByUsername_Querydsl("member1");
        //then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember1).containsExactly(member);
        assertThat(findMember2).containsExactly(member);

     }

     @Test
     public void searchTest() throws Exception{
       //given
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

         //when
         MemberSearchCondition condition = new MemberSearchCondition();
         condition.setAgeGoe(15);
         condition.setAgeLoe(20);
         condition.setUsername("member2");

//         List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
         List<MemberTeamDto> result = memberJpaRepository.searchByWhere(condition);

         //then
         assertThat(result).extracting("teamName").containsExactly("teamA");

      }
}
