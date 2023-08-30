package com.ninetosix.apimodule.nts.controller;

import com.ninetosix.apimodule.nts.dto.company.CompaniesResDTO;
import com.ninetosix.apimodule.nts.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @ApiOperation(value = "회사 목록")
    @GetMapping("companies")
    public ResponseEntity<List<CompaniesResDTO>> companies() {
        return ResponseEntity.ok(companyService.companies());
    }
}
