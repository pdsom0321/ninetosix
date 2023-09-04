package com.ninetosix.adminmodule.nts.controller;

import com.ninetosix.adminmodule.nts.dto.company.CompanyReqDTO;
import com.ninetosix.adminmodule.nts.dto.company.CompanyResDTO;
import com.ninetosix.adminmodule.nts.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @ApiOperation(value = "회사 등록")
    @PostMapping("company")
    public ResponseEntity<Void> createCompany(@RequestBody CompanyReqDTO reqDTO) {
        companyService.createCompany(reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회사 목록 조회")
    @GetMapping ("company")
    public ResponseEntity<List<CompanyResDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @ApiOperation(value = "회사 상세 조회")
    @GetMapping ("company/{id}")
    public ResponseEntity<CompanyResDTO> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @ApiOperation(value = "회사 정보 수정")
    @PutMapping("company/{id}")
    public ResponseEntity<Void> modifyCompany(@PathVariable Long id, @RequestBody CompanyReqDTO reqDTO) {
        companyService.modifyCompany(id, reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회사 삭제")
    @DeleteMapping ("company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
