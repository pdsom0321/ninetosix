package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.nts.dto.team.TeamsResDTO;
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
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;

    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<TeamsResDTO> teams(Long companyId) {
        return teamRepository.findAllByCompanyId(companyId).stream()
                .map(TeamsResDTO::of)
                .collect(Collectors.toList());
    }
}
