package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Order;
import com.example.entityrelation.domain.QDelivery;
import com.example.entityrelation.domain.QOrder;
import com.example.entityrelation.dto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.entityrelation.domain.QDelivery.delivery;
import static com.example.entityrelation.domain.QMember.member;
import static com.example.entityrelation.domain.QOrder.order;
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

    @Override
    public Page<MemberTeamDto> searchPageSimple(MembersearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MembersearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                        nameEq(condition.getName()),
                        teanNameEq(condition.getTeamName()),
                        ageCoe(condition.getAgeCoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetchCount();


        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<OrderDto> searchFetchPageDto(MembersearchCondition condition, Pageable pageable) {

        QueryResults<OrderDto> results = queryFactory
                .select(new QOrderDto(order))
                .from(order)
                .leftJoin(order.member, member).fetchJoin()
                .leftJoin(order.delivery, delivery).fetchJoin()
                .where(
                        nameEq(condition.getName()),
                        teanNameEq(condition.getTeamName()),
                        ageCoe(condition.getAgeCoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<OrderDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Order> searchFetchPage(MembersearchCondition condition, Pageable pageable) {
            QueryResults<Order> results = queryFactory
                    .selectFrom(order)
                    .leftJoin(order.member, member).fetchJoin()
                    .leftJoin(order.delivery, delivery).fetchJoin()
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();

        long total = results.getTotal();
        List<Order> content = results.getResults();
        return new PageImpl<>(content, pageable, total);
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
