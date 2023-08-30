package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByEmailAndExpireDateGreaterThan(String email, Date now);
    void deleteByEmail(String email);
}
