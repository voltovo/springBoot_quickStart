package hello.core.order;

import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();


    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member suman = new Member(memberId, "suman", Grade.VIP);
        memberService.join(suman);
        //when
        Order itemA = orderService.createOrder(memberId, "itemA", 10000);
        //then
        assertEquals(itemA.getDiscountPrice(), 1000);
        assertEquals(itemA.calculatePrice(), 9000);
    }
}
