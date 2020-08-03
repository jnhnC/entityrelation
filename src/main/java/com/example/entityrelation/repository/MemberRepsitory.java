package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepsitory extends JpaRepository<Member,Long> {

}
