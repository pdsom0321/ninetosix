package com.ninetosix.adminmodule.nts.member.service;

import com.ninetosix.adminmodule.nts.member.dto.MemberResDTO;
import com.ninetosix.adminmodule.nts.member.dto.MembersResDTO;
import com.ninetosix.coremodule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public List<MembersResDTO> members(long teamId) {
        return memberRepository.findAllByTeamId(teamId).stream()
                .map(MembersResDTO::of)
                .collect(Collectors.toList());
    }

    public MemberResDTO member(long teamId, long id) {
        return memberRepository.findByIdAndTeamId(id, teamId)
                .map(MemberResDTO::of)
                .orElseThrow(EntityNotFoundException::new);
    }
}
