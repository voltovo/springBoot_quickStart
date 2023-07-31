package com.rubypaper;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataInitializeTest {
    @Autowired
    private MemberRepository memberRepo;

    @Test
    public void testDataInsert(){
        Member member1 = new Member();
        member1.setId("member");
        member1.setPassword("member123");
        member1.setName("회원");
        member1.setRole(Role.ROLE_MEMBER);
        member1.setEnabled(true);
        memberRepo.save(member1);

        Member member2 = new Member();
        member2.setId("manager");
        member2.setPassword("manager123");
        member2.setName("매니저");
        member2.setRole(Role.ROLE_MANAGER);
        member2.setEnabled(true);
        memberRepo.save(member2);

        Member member3 = new Member();
        member3.setId("admin");
        member3.setPassword("admin123");
        member3.setName("관리자");
        member3.setRole(Role.ROLE_ADMIN);
        member3.setEnabled(true);
        memberRepo.save(member3);
    }
}
