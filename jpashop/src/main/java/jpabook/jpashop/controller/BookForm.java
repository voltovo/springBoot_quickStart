package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    /**
     * 입력 받은 책 정보로 상품 생성
     * @return
     */
    public Book createBook() {
        Book book = new Book();
        book.setName(this.name);
        book.setPrice(this.price);
        book.setStockQuantity(this.stockQuantity);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);

        return book;
    }
}
