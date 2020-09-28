package com.example.entityrelation.repository;

import com.example.entityrelation.dto.MemberTeamDto;
import com.example.entityrelation.dto.MembersearchCondition;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MembersearchCondition condition);
}
