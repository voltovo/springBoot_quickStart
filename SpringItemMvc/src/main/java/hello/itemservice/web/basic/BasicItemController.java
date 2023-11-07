package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, @ModelAttribute("item") Item updateItem) {
        itemRepository.update(itemId, updateItem);

        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 5));
    }
}
