package com.ninetosix.coremodule.repository;


import com.ninetosix.coremodule.entity.AuthenticationCode;
import com.ninetosix.coremodule.vo.AuthenticationCodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuthenticationCodeRepository extends JpaRepository<AuthenticationCode, Long> {
    Optional<AuthenticationCode> findByCodeAndEmailAndTypeAndExpiryDateGreaterThanAndIsCodeEntered(int code, String email, AuthenticationCodeType type, LocalDateTime currentTime, Boolean isCodeEntered);
}
