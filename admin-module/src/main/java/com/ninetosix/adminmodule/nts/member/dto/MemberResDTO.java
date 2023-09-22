package com.ninetosix.adminmodule.nts.member.dto;

import com.ninetosix.coremodule.entity.Member;
import com.ninetosix.coremodule.vo.YNCode;

import java.time.LocalDateTime;

public record MemberResDTO(
        long id,
        String email,
        String contact,
        YNCode deleteYn,
        YNCode pushAgreeYn,
        LocalDateTime insertDate,
        LocalDateTime updateDate,
        String teamName,
        String companyName) {
    public static MemberResDTO of(Member member) {
        return new MemberResDTO(member.getId(), member.getEmail(), member.getContact(), member.getDeleteYn(), member.getPushAgreeYn(), member.getInsertDate(), member.getUpdateDate(), member.getTeam().getName(), member.getCompany().getName());
    }
}
