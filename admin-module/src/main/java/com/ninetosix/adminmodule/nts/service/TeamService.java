package com.ninetosix.adminmodule.nts.service;

import com.ninetosix.adminmodule.nts.dto.team.TeamReqDTO;
import com.ninetosix.adminmodule.nts.dto.team.TeamsResDTO;
import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.entity.Team;
import com.ninetosix.coremodule.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final CompanyService companyService;
    private final TeamRepository teamRepository;

    public void createTeam(TeamReqDTO reqDTO) {
        Company company = companyService.getCompany(reqDTO.companyId());
        teamRepository.save(Team.create(reqDTO.name(), company));
    }

    public List<TeamsResDTO> getTeams(Long companyId) {
        return teamRepository.findAllByCompanyId(companyId).stream()
                .map(TeamsResDTO::of)
                .collect(Collectors.toList());
    }

    public void modifyTeam(Long id, String name) {
        Team team = teamRepository.findById(id).orElseThrow(NoSuchElementException::new);
        team.modify(name);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

}
