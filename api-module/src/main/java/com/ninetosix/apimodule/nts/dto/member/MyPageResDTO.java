package com.ninetosix.apimodule.nts.dto.member;

import com.ninetosix.coremodule.entity.Member;

public record MyPageResDTO(String email, String name, String contact, String companyName, String teamName) {
    public static MyPageResDTO of(Member member) {
        return new MyPageResDTO(member.getEmail(), member.getName(), member.getContact(), member.getCompany().getName(), member.getTeam().getName());
    }
}
