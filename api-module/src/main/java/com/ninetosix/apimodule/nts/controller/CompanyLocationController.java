package com.ninetosix.apimodule.nts.controller;

import com.ninetosix.apimodule.nts.dto.companylocation.CompanyLocationsResDTO;
import com.ninetosix.apimodule.nts.service.CompanyLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CompanyLocationController {

    private final CompanyLocationService companyLocationService;

    @GetMapping("/company-locations")
    public ResponseEntity<List<CompanyLocationsResDTO>> companyLocations() {
        return ResponseEntity.ok(companyLocationService.companyLocations());
    }
}
