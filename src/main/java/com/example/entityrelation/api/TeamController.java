package com.example.entityrelation.api;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Team;
import com.example.entityrelation.repository.TeamRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    @GetMapping("/api/teams")
    public Page<TeamDto> teams(Pageable pageable){
       return teamRepository.findAll(pageable).map(TeamDto::new);
    }

    @Data
    private class TeamDto {
        private Long teamId;
        private String teamName;
        private List<MemberDto> memberDtoList;

        public TeamDto(Team team){
            teamId = team.getId();
            teamName = team.getName();
            memberDtoList = team.getMembers().stream().map(MemberDto::new).collect(toList());
        }

    }

    @Data
    private class MemberDto{
        private String name;
        private int age;

        public MemberDto(Member member){
            name = member.getName();
            age = member.getAge();
        }

    }


}
