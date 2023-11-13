package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        // 명령과 쿼리를 분리하라
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
