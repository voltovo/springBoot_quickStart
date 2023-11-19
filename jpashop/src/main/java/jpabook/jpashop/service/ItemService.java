package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 상품 저장
     * @param item
     */
    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    /**
     * 상품 목록 조회
     * @return
     */
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    /**
     * 상품 조회
     * @param itemId
     * @return
     */
    public Item find(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
