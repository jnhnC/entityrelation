package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepsitory extends JpaRepository<Member,Long> {


}
