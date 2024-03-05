package study.querydsl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;

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
}
