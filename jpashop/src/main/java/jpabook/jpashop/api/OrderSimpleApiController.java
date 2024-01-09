package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    @GetMapping("/api/v2/simple-orders")
    public Result ordersV2(){
        //Order 2개
        //N + 1 -> 1 + 회원 N + 배송 N
        List<SimpleOrderDto> result = orderRepository.findAll(new OrderSearch()).stream().map(o -> new SimpleOrderDto(o))
            .collect(Collectors.toList());
        return new Result(result.size(), result);
    }

    @GetMapping("/api/v3/simple-orders")
    public Result ordersV3() {
        List<SimpleOrderDto> result = orderRepository.findAllWithMemberDelivery().stream().map(o -> new SimpleOrderDto(o))
            .collect(Collectors.toList());

        return new Result(result.size(), result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private Integer orderCount;
        private T data;
    }

    @Data
    static class SimpleOrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
