package com.ninetosix.adminmodule.nts.member.controller;

import com.ninetosix.adminmodule.nts.member.dto.MemberResDTO;
import com.ninetosix.adminmodule.nts.member.dto.MembersResDTO;
import com.ninetosix.adminmodule.nts.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "팀 별 사용자 목록")
    @GetMapping("member/{teamId}")
    public ResponseEntity<List<MembersResDTO>> members(@PathVariable long teamId) {
        return ResponseEntity.ok(memberService.members(teamId));
    }

    @ApiOperation(value = "사용자 상세 정보")
    @GetMapping("member/{teamId}/{id}")
    public ResponseEntity<MemberResDTO> member(@PathVariable long teamId, @PathVariable long id) {
        return ResponseEntity.ok(memberService.member(teamId, id));
    }
}
