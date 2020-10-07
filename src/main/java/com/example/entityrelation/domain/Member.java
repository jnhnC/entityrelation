package com.example.entityrelation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "age", "name"})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private int age;

    private String name;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    public Member(String name, int age, String city, String streets, String zipcode, Team team) {
        this.name = name;
        this.age = age;
        this.address = new Address(city, streets, zipcode);
        if (team != null) {
            changeTeam(team);
        }

    }

    public Member(String name) {
        this.name = name;
    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}