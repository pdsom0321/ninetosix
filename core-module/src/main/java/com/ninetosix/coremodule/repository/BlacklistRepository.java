package com.ninetosix.coremodule.repository;


import com.ninetosix.coremodule.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    boolean existsByAccessToken(String accessToken);
}
