package com.example.entityrelation.api;


import com.example.entityrelation.domain.Member;
import com.example.entityrelation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void update(Long id, String name) {
        Optional<Member> member = memberRepository.findById(id);
        new Member(member.get().getName());

    }
}
