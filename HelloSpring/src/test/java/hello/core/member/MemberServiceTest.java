package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member suman = new Member(1L, "suman", Grade.VIP);
        //when
        memberService.join(suman);
        Member findMember = memberService.findMember(1L);
        //then
        assertThat(suman).isEqualTo(findMember);
    }

}
