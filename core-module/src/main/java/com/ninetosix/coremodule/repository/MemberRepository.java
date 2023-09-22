package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    List<Member> findAllByTeamId(Long teamId);
    Boolean existsByEmail(String email);
    Optional<Member> findByIdAndTeamId(long id, long teamId);
}
