package com.example.entityrelation.repository;

import com.example.entityrelation.dto.MemberTeamDto;
import com.example.entityrelation.dto.MembersearchCondition;
import com.example.entityrelation.dto.QMemberTeamDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.entityrelation.domain.QMember.member;
import static com.example.entityrelation.domain.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MembersearchCondition condition) {
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.name,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        nameEq(condition.getName()),
                        teanNameEq(condition.getTeamName()),
                        ageCoe(condition.getAgeCoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch();
    }
    private BooleanExpression nameEq(String name) {
        return hasText(name) ? member.name.eq(name) : null;
    }

    private BooleanExpression teanNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageCoe(Integer ageCoe) {
        return ageCoe != null ? member.age.goe(ageCoe) : null;
    }
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
