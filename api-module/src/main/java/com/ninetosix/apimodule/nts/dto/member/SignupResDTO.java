package com.ninetosix.apimodule.nts.dto.member;

import com.ninetosix.coremodule.entity.Member;

public record SignupResDTO(String email, String name) {
    public static SignupResDTO of(Member member) {
        return new SignupResDTO(member.getEmail(), member.getName());
    }
}
