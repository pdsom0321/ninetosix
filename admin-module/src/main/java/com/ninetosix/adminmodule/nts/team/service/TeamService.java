package com.ninetosix.adminmodule.nts.team.service;

import com.ninetosix.adminmodule.nts.company.service.CompanyService;
import com.ninetosix.adminmodule.nts.team.dto.TeamReqDTO;
import com.ninetosix.adminmodule.nts.team.dto.TeamsResDTO;
import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.entity.Team;
import com.ninetosix.coremodule.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final CompanyService companyService;
    private final TeamRepository teamRepository;

    public void createTeam(TeamReqDTO reqDTO) {
        Company company = companyService.company(reqDTO.companyId());
        teamRepository.save(Team.create(reqDTO.name(), company));
    }

    public List<TeamsResDTO> teams(long companyId) {
        return teamRepository.findAllByCompanyId(companyId).stream()
                .map(team -> new TeamsResDTO(team.getId(), team.getName()))
                .collect(Collectors.toList());
    }

    public void modifyTeam(long id, String name) {
        Team team = teamRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        team.modify(name);
    }

    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }

}
