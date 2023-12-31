package com.ninetosix.adminmodule.nts.company.controller;

import com.ninetosix.adminmodule.nts.company.dto.CompanyReqDTO;
import com.ninetosix.adminmodule.nts.company.dto.CompanyResDTO;
import com.ninetosix.adminmodule.nts.company.service.CompanyService;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
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
    public ResponseEntity<List<CompanyResDTO>> companies(Principal principal) {
        // 현재 로그인한 사용자 정보 가져오기 principal.getName()
        System.out.println("principal.getName() = " + principal.getName());

        return ResponseEntity.ok(companyService.companies());
    }

    @ApiOperation(value = "회사 정보 수정")
    @PutMapping("company/{id}")
    public ResponseEntity<Void> modifyCompany(@PathVariable long id, @RequestBody CompanyReqDTO reqDTO) {
        companyService.modifyCompany(id, reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회사 삭제")
    @DeleteMapping ("company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
