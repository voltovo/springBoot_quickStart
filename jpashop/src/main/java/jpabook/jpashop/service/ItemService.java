package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.controller.BookForm;
import jpabook.jpashop.domain.item.Book;
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
     * 상품 수정
     * @param itemId
     * @param form
     */
    @Transactional
    public void updateItem(Long itemId, BookForm form) {
        // 로그인한 유저가 해당 item을 수정할 권한이 있는지 체크가 필요하다
        Book findItem = (Book) itemRepository.findOne(itemId);
        findItem.setName(form.getName());
        findItem.setPrice(form.getPrice());
        findItem.setStockQuantity(form.getStockQuantity());
        findItem.setAuthor(form.getAuthor());
        findItem.setIsbn(form.getIsbn());
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
