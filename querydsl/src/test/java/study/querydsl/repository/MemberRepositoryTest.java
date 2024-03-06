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
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void basicTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        List<Member> findMember1 = memberRepository.findAll();

        List<Member> findMember2 = memberRepository.findByUsername("member1");
        //then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember1).containsExactly(member);
        assertThat(findMember2).containsExactly(member);

    }
}
