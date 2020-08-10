package com.example.entityrelation.api;

import com.example.entityrelation.domain.item.Item;
import com.example.entityrelation.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping("/api/item")
    public List<Item> itemList(){
        return itemRepository.findAll();
    }

}
