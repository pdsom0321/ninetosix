package com.ninetosix.apimodule.nts.controller;

import com.ninetosix.apimodule.nts.dto.team.TeamsResDTO;
import com.ninetosix.apimodule.nts.service.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @ApiOperation(value = "회사의 팀 목록")
    @GetMapping("teams/{companyId}")
    public ResponseEntity<List<TeamsResDTO>> teams(@PathVariable Long companyId) {
        return ResponseEntity.ok(teamService.teams(companyId));
    }

}
