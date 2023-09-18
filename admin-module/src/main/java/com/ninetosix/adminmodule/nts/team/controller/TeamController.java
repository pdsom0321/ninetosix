package com.ninetosix.adminmodule.nts.team.controller;

import com.ninetosix.adminmodule.nts.team.dto.TeamModifyReqDTO;
import com.ninetosix.adminmodule.nts.team.dto.TeamReqDTO;
import com.ninetosix.adminmodule.nts.team.dto.TeamsResDTO;
import com.ninetosix.adminmodule.nts.team.service.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @ApiOperation(value = "팀 등록")
    @PostMapping("team")
    public ResponseEntity<Void> createTeam(@RequestBody TeamReqDTO reqDTO) {
        teamService.createTeam(reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회사 별 팀 목록")
    @GetMapping("team/{companyId}")
    public ResponseEntity<List<TeamsResDTO>> teams(@PathVariable long companyId) {
        return ResponseEntity.ok(teamService.teams(companyId));
    }

    @ApiOperation(value = "팀 정보 수정")
    @PutMapping("team/{id}")
    public ResponseEntity<Void> modifyTeam(@PathVariable long id, @RequestBody TeamModifyReqDTO reqDTO) {
        teamService.modifyTeam(id, reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "팀 삭제")
    @DeleteMapping("team/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
