package com.example.entityrelation.api;

import com.example.entityrelation.domain.CategoryItem;
import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.domain.item.Item;
import com.example.entityrelation.repository.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        private List<OrderItemDto> orderItems;
        private List<CategoryItemDto> categoryItems ;

        public ItemDto(Item item){
            name = item.getName();
            price= item.getPrice();
            stockQuantity = item.getStockQuantity();
            orderItems = item.getOrderItems().stream().map(OrderItemDto::new).collect(toList());
            categoryItems = item.getCategoryItems().stream().map(CategoryItemDto::new).collect(toList());

        }
    }

    @Data
    static class OrderItemDto {
        private int orderPrice;
        private int orderCount;

        public OrderItemDto(OrderItem orderItem){
            orderPrice = orderItem.getOrderPrice();
            orderCount = orderItem.getCount();

        }
    }

    @Data
    static class CategoryItemDto {
        private String categoryName;

        public CategoryItemDto(CategoryItem categoryItem){
            categoryName = categoryItem.getCategory().getName();
        }
    }
}
