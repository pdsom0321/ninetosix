package com.ninetosix.apimodule.nts.dto.team;

import com.ninetosix.coremodule.entity.Team;

public record TeamsResDTO(Long id, String name) {
    public static TeamsResDTO of(Team team) {
        return new TeamsResDTO(team.getId(), team.getName());
    }
}
