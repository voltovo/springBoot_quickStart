package jpabook.jpashop.controller;

import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 상품 정보 입력 창 호출
     * @param model
     * @return
     */
    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    /**
     * 상품 정보 저장
     * @param form
     * @return
     */
    @PostMapping("/items/new")
    public String create(BookForm form) {
        // 화면에서 입력 받은 정보 저장
        itemService.save(form.createBook());
        return "redirect:/";
    }
}
