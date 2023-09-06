package hello.core.discount;

import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void discount() {
        // given
        int itemPrice = 10000;
        Member suman = new Member(1L, "suman", Grade.VIP);
        // when
        int discount = rateDiscountPolicy.discount(suman, itemPrice);
        // then
        assertEquals(1000, discount);
    }

    @Test
    @DisplayName("VIP는 10% 할인 적용 실패.")
    void discount_fail() {
        // given
        int itemPrice = 20000;
        Member suman = new Member(2L, "spring", Grade.BASIC);
        // when
        int discount = rateDiscountPolicy.discount(suman, itemPrice);
        // then
        assertEquals(0, discount);
    }
}
