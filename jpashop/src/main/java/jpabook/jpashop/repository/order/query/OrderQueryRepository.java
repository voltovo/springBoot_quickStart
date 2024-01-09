package jpabook.jpashop.repository.order.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        for (OrderQueryDto o : result) {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        }

        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) from OrderItem oi"
                    + " join oi.item i" + " where oi.order.id = :orderId", OrderItemQueryDto.class)
            .setParameter("orderId", orderId).getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
            "select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o"
                + " join o.member m" + " join o.delivery d", OrderQueryDto.class).getResultList();
    }

    public List<OrderQueryDto> findAllByDto() {
        // 주문 정보 조회
        // 쿼리 1번 실행
        List<OrderQueryDto> result = findOrders();
        // 주문 정보 중에 아이템 아아디 조회
        List<Long> orderIds = toOrderIds(result);
        // 아이템 아이디 리스트를 IN절에 넣어서 한번에 조회
        // 쿼리 1번 실행
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);
        // Map의 key, value로 아이템 정보 입력
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) from OrderItem oi"
                    + " join oi.item i" + " where oi.order.id in :orderIds", OrderItemQueryDto.class)
            .setParameter("orderIds", orderIds).getResultList();
        // 조회된 아이템 정보를 Map으로 변환
        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
            .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
        return orderItemMap;
    }

    private static List<Long> toOrderIds(List<OrderQueryDto> result) {
        return result.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
    }
}
