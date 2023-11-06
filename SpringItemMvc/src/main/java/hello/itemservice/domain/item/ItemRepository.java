package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    // 멀티쓰레드 환경에서는 ConcurrentHashMap를 사용해야 한다.
    private static final Map<Long, Item> store = new HashMap<>();
    // 멀티쓰레드 환경에서는 Atomic을 사용해야 한다
    private static long sequence = 0;

    // 상품 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 상품 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    // 상품 목록 조회
    public List<Item> findAll(){
        // ArrayList로 감싸서 반환하는 이유
        // ArrayList에 값을 추가할 경우 store의 값이 변하지 않는다
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // test용
    public void clear(){
        store.clear();
    }
}
