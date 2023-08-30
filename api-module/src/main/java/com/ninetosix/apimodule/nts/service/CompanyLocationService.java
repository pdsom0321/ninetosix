package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.core.jwt.MemberContext;
import com.ninetosix.apimodule.nts.dto.companylocation.CompanyLocationsResDTO;
import com.ninetosix.coremodule.entity.Member;
import com.ninetosix.coremodule.repository.CompanyLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyLocationService {
    private final CompanyLocationRepository companyLocationRepository;

    private final MemberService memberService;

    public List<CompanyLocationsResDTO> companyLocations() {
        Long memberId = MemberContext.getMemberId();
        Member member = memberService.findById(memberId);

        return companyLocationRepository.findByCompany(member.getCompany()).stream()
                .map(CompanyLocationsResDTO::createCompanyLocation)
                .collect(Collectors.toList());
    }
}
