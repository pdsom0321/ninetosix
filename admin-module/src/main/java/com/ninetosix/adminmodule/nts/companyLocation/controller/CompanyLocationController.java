package com.ninetosix.adminmodule.nts.companyLocation.controller;

import com.ninetosix.adminmodule.nts.companyLocation.dto.CompanyLocationCreateReqDTO;
import com.ninetosix.adminmodule.nts.companyLocation.dto.CompanyLocationsResDTO;
import com.ninetosix.adminmodule.nts.companyLocation.service.CompanyLocationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyLocationController {
    private final CompanyLocationService companyLocationService;

    @ApiOperation(value = "회사 와 위치 매핑")
    @PostMapping("/company-location")
    public ResponseEntity<Void> createCompanyLocation(@RequestBody CompanyLocationCreateReqDTO reqDTO) {
        companyLocationService.createCompanyLocation(reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회사 별 위치 목록")
    @GetMapping("/company-location/{companyId}")
    public ResponseEntity<List<CompanyLocationsResDTO>> companyLocations(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyLocationService.companyLocations(companyId));
    }
}
