package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByCompanyId(Long companyId);
}
