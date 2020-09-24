package com.example.entityrelation.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.entityrelation.domain.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory ;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void startQuerydsl(){
        Member fetchOne = queryFactory
                .select(member)
                .from(member)
                .where(member.name.eq("testB"))
                .fetchOne();

        System.out.println("fetchOne = " + fetchOne);
    }

    @Test
    public void dynamicQuery_BooleanBuilder(){
        String nameParam = "testB";
        Integer ageParam = 35;

        List<Member> result = searchMember(nameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember(String nameParam, Integer ageParam) {
        BooleanBuilder builder = new BooleanBuilder();
        if(nameParam != null){
            builder.and(member.name.eq(nameParam));
        }

        if(ageParam != null){
            builder.and(member.age.eq(ageParam));
        }

        return queryFactory
            .selectFrom(member)
            .where(builder)
            .fetch();

    }

    @Test
    public void dynamicQuery_WhereParam(){
        String nameParam = "testB";
        Integer ageParam = 35;

        List<Member> result = searchMember2(nameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String nameParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
                .where(allEq(nameParam, ageParam))
                .fetch();
    }

    private List<Member> searchMember3(String nameParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
                .where(allEq(nameParam, ageParam))
                .fetch();
    }

    private BooleanExpression usernameEq(String nameParam) {
        return nameParam != null? member.name.eq(nameParam) : null;
    }

    private BooleanExpression ageEq(Integer ageParam) {
        return ageParam != null? member.age.eq(ageParam) : null;
    }

    private BooleanExpression allEq(String nameParam, Integer ageParam) {
        return usernameEq(nameParam).and(ageEq(ageParam));
    }

    @Test
    public void bulkUpdate(){
        long count = queryFactory
                .update(member)
                .set(member.name, "testE")
                .where(member.age.lt(35))
                .execute();

        em.flush();
        em.clear();

        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();
        for (Member fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1);
        }
    }

    @Test
    public void bulkAdd(){
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();

    }
    @Test
    public void bulkDelete(){
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(25))
                .execute();
    }

}

