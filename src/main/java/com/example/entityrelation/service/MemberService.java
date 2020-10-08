package com.example.entityrelation.service;


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
        member.get().updateName(name);



    }
}
