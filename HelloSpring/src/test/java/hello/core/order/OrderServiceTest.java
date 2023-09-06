package hello.core.order;

import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = ac.getBean("memberService", MemberService.class);
        orderService = ac.getBean("orderService", OrderService.class);
    }

    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member suman = new Member(memberId, "suman", Grade.VIP);
        memberService.join(suman);
        //when
        Order itemA = orderService.createOrder(memberId, "itemA", 20000);
        //then
        assertEquals(itemA.getDiscountPrice(), 2000);
        assertEquals(itemA.calculatePrice(), 18000);
    }
}
