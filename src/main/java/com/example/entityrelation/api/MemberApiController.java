package com.example.entityrelation.api;

import com.example.entityrelation.domain.*;
import com.example.entityrelation.repository.MemberRepository;
import com.example.entityrelation.repository.TeamRepository;
import com.example.entityrelation.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepsitory;

    //가짜 엔티티 Member 불러 오기
    @GetMapping("/api/members")
    public List<Member> orders() {
        return memberRepository.findAll();
    }

    //DTO를 이용한 가짜 엔티티 member 불러오기
    @GetMapping("/api/membersCollection")
    public List<MemberDto> membersCollection() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> result = members.stream().map(MemberDto::new)
                .collect(toList());

        return result;
    }

    //DTO를 이용한 가짜 엔티티 member 불러오기
    @GetMapping("/api/membersCollectionPaging")
    public Page<MemberDto> membersCollectionPaging(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberDto> toMap = members.map(MemberDto::new);
        //  Slice<Member> members = memberRepsitory.findAll(pageRequest);
        //  List<MemberDto> result = members.stream().map(MemberDto::new)
        //      .collect(toList());
        return toMap;
    }

    @GetMapping("/api/searchFetchPage")
    public Page<MemberDto> searchFetchPage(Pageable pageable){
        return memberRepository.searchMembers(pageable).map(MemberDto::new);
    }

    @PostMapping("/api/upload")
    public void searchUpload(@RequestParam("file") MultipartFile file){
        System.out.println("file = " + file.getOriginalFilename());
        try {


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @PostMapping("/api/member")
    public MemberDto saveMember(@RequestBody @Valid MemberRequest request) {
        Team team = teamRepsitory.findByName(request.teamName);

        Member member = new Member(request.name, request.age, request.city, request.street, request.zipcode, team);
        Member saveMember = memberRepository.save(member);
        MemberDto memberDto = new MemberDto(saveMember);

        return memberDto;

    }

    @PutMapping("/api/member/{id}")
    public MemberDto updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request ){
        memberService.update(id, request.getName());
        Optional<Member> byId = memberRepository.findById(id);
        return new MemberDto(byId.get());
    }


    @Data
    static class MemberDto {
        private String name;
        private int age;
        private Address address;
        private String teamName;
        private LocalDateTime createDt;
        private LocalDateTime updateDt;
        private List<OrderDto> orders;

        public MemberDto(Member member) {
            name = member.getName();
            age = member.getAge();
            address = member.getAddress();
            teamName = member.getTeam().getName();
            createDt = member.getCreatedDate();
            updateDt = member.getUpdatedDate();
            orders = member.getOrders().stream()
                    .map(orders -> new OrderDto(orders))
                    .collect(toList());
        }
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private LocalDateTime orderDate;
        private DeliveryStatus status;
        private List<OrderItemDto> orderItems;


        public OrderDto(Order order) {
            orderId = order.getId();
            orderDate = order.getOrderDate();
            status = order.getDelivery().getStatus();
            orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());

        }

    }

    @Data
    static class OrderItemDto {
        private String itemName;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();

        }

    }

    @Data
    @NoArgsConstructor
    static class MemberRequest {
        private String name;
        private int age;
        private String city;
        private String street;
        private String zipcode;
        private String teamName;

        public MemberRequest(Member member) {
            this.name = member.getName();
            this.age = member.getAge();
            this.city = member.getAddress().getCity();
            this.street = member.getAddress().getStreet();
            this.zipcode = member.getAddress().getZipcode();
            this.teamName = member.getTeam().getName();
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberRequest {
        private Long id;
        private String name;

        public UpdateMemberRequest(Member member) {
            this.id = member.getId();
            this.name = member.getName();
        }
    }
}
