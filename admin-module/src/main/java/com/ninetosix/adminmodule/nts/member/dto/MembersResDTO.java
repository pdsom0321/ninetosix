package com.ninetosix.adminmodule.nts.member.dto;

import com.ninetosix.coremodule.entity.Member;
import com.ninetosix.coremodule.vo.YNCode;

public record MembersResDTO(
        long id,
        String email,
        String name,
        YNCode deleteYn,
        long teamId,
        String teamName,
        String companyName) {
    public static MembersResDTO of(Member member) {
        return new MembersResDTO(member.getId(), member.getEmail(), member.getName(), member.getDeleteYn(), member.getTeam().getId(), member.getTeam().getName(), member.getCompany().getName());
    }
}
