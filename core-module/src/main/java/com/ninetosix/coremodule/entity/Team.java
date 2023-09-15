package com.ninetosix.coremodule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    public static Team create(String name, Company company) {
        return Team.builder()
                .name(name)
                .company(company)
                .build();
    }

    public void modify(String name) {
        this.name = name;
    }
}
