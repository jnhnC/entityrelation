package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Team;
import com.example.entityrelation.dto.MemberTeamDto;
import com.example.entityrelation.dto.MembersearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;


    @Autowired
    MemberRepository memberRepository;

    @Test
    public void basicTest(){
        Team team = new Team("teamC");
        Member member = new Member("testC",24,"수원", "영통", "331-9", team);
        memberJpaRepository.save(member);
//        em.clear();
//        em.flush();

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

//        List<Member> result = memberJpaRepository.findAll_Querydsl();
//        assertThat(result).containsExactly(member);

        List<Member> result2 = memberJpaRepository.findByName_Querydsl("testC");
        assertThat(result2).containsExactly(member);

    }

    @Test
    public void searchTest(){

        MembersearchCondition membersearchCondition = new MembersearchCondition();
        membersearchCondition.setAgeCoe(30);
        membersearchCondition.setAgeLoe(40);
        membersearchCondition.setTeamName("teamB");

        List<MemberTeamDto> result = memberRepository.search(membersearchCondition);
        assertThat(result).extracting("name").containsExactly("testB");

    }

}