package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.dto.MemberTeamDto;
import com.example.entityrelation.dto.MembersearchCondition;
import com.example.entityrelation.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MembersearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MembersearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MembersearchCondition condition, Pageable pageable);
    Page<OrderDto> searchFetchPageDto(MembersearchCondition condition, Pageable pageable);
    Page<Order> searchFetchPage(MembersearchCondition condition, Pageable pageable);
    Page<Member> searchMembers(Pageable pageable);

}
