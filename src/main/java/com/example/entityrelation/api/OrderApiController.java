package com.example.entityrelation.api;

import com.example.entityrelation.domain.DeliveryStatus;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.dto.MembersearchCondition;
import com.example.entityrelation.dto.OrderDto;
import com.example.entityrelation.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final MemberRepository memberRepository;
    private final OrderRepsitory orderRepsitory;
    private final OrderQueryRepository orderQueryRepository;


    //주인 엔티티 Order 불러 오기
    @GetMapping("/api/orders")
    public List<Order> orders(){
        return orderRepsitory.findAll();
    }


    //DTO 사용하기
    @GetMapping("/api/ordersDto")
    public List<OrderDto> ordersDto(){
        List<Order> orders =orderRepsitory.findFetchOrderMember();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    //페치조인 사용하기
    @GetMapping("/api/ordersFetch")
    public List<OrderDto> ordersFetch(){
        List<Order> orders =orderRepsitory.findFetchAll();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    //페치조인 사용하기
//    @GetMapping("/api/ordersFetchPaging")
//    public Page<OrderDto> ordersFetchPaging(){
//
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "member_id"));
//
//        Page<Order> orders = orderRepsitory.findFetchPaging(pageRequest);
//        Page<OrderDto> orderDtos = orders.map(o -> new OrderDto(o));
//
//        return orderDtos;
//    }

    //페이징 변경
    @GetMapping("/api/ordersPage")
    public Page<OrderDto> ordersPage(){

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "member_id"));
        Page<Order> orders = orderRepsitory.findAll(pageRequest);
        Page<OrderDto> orderDtos = orders.map(m -> new OrderDto(m));


        return orderDtos;
    }

    //다중페치조인 페이징
    @GetMapping("/api/ordersFetchPaging")
    public List<OrderDto> ordersFetchPaging(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit ){

        List<Order> orders =orderQueryRepository.findFetchPaging(offset, limit);

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    //다중페치조인 페이징(queryDsl)-DTO로 바로 받기
    @GetMapping("/api/ordersFetchPageDto")
    public Page<com.example.entityrelation.dto.OrderDto> ordersFetchPagingQueryDslDto(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "3") int limit ){

        MembersearchCondition condition= new MembersearchCondition();
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<com.example.entityrelation.dto.OrderDto> orders =memberRepository.searchFetchPageDto(condition,pageRequest);

        return orders;
    }

    //다중페치조인 페이징(queryDsl)-Order받고 OrderDto 적용하기 - 최적이라 생각
    @GetMapping("/api/ordersFetchPage")
    public Page<OrderDto> ordersFetchPagingQueryDsl(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        MembersearchCondition condition = new MembersearchCondition();
        return memberRepository.searchFetchPage(condition,pageable).map(OrderDto::new);
    }

    //DTO 정의
    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private String address;
        private DeliveryStatus status;
        private List<OrderItemDto> orderItems ;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            address = order.getMember().getAddress().getCity()
                    +""+ order.getMember().getAddress().getStreet();
            status = order.getDelivery().getStatus();
            orderItems =  order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());

        }

        @Data
        static class OrderItemDto {

            private Long id;
            private int orderPrice;
            private String itemName ;

            public OrderItemDto(OrderItem orderItem){
                id = orderItem.getId();
                orderPrice = orderItem.getOrderPrice();
                itemName = orderItem.getItem().getName();
            }

        }
    }

}
