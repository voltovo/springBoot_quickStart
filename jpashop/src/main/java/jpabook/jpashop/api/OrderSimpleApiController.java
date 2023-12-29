package jpabook.jpashop.api;

import java.util.List;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Order  조회
 * Order -> Member
 * Order -> Delivery
 *
 * xToOne (ManyToOne, OneToOne)
 * 에서의 성능 최적화 예제
 *
 * 양방향 연관관계면 둘 중엔 한 군데에 jsonIgnore를 추가해서 끊어줘야 한다.
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();    // 회원 엔티티 Lazy 로딩을 강제 초기화
            order.getDelivery().getStatus();// 배송 엔티티 Lazy 로딩을 강제 초기화
        }
        return all;
    }
}
