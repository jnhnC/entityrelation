package com.example.entityrelation.api;

import com.example.entityrelation.domain.item.Item;
import com.example.entityrelation.repository.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping("/api/item")
    public List<ItemDto> itemList(){
        List<Item> items = itemRepository.findAll();

        return items.stream().map(ItemDto::new)
                .collect(toList());
    }

    @Data
    private class ItemDto {
        private String name;
        private int price;
        private int stockQuantity;

        public ItemDto(Item item){
            name = item.getName();
            price= item.getPrice();
            stockQuantity = item.getStockQuantity();

        }

    }
}
