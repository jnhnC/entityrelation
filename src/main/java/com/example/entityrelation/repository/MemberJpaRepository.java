package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.dto.MemberTeamDto;
import com.example.entityrelation.dto.MembersearchCondition;
import com.example.entityrelation.dto.QMemberTeamDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javassist.compiler.MemberCodeGen;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.entityrelation.domain.QMember.member;
import static com.example.entityrelation.domain.QTeam.team;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory ;

    public MemberJpaRepository(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    public void save(Member member){
        em.persist(member);

    }

    public Optional<Member> findById(Long id){
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);

    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findAll_Querydsl(){
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where  m.name =:name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Member> findByName_Querydsl(String name){
        return queryFactory
                .selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }

    public List<MemberTeamDto> searchByBuilder(MembersearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();
        if (hasText(condition.getName())) {
            builder.and(member.name.eq(condition.getName()));
        }
        if (hasText((condition.getTeamName()))) {
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if(condition.getAgeCoe() !=null){
            builder.and(member.age.goe(condition.getAgeCoe()));
        }
        if (condition.getAgeCoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }


        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.name,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    public List<MemberTeamDto> search(MembersearchCondition condition){
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
