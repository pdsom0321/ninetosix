package com.ninetosix.adminmodule.nts.controller;

import com.ninetosix.adminmodule.nts.dto.CompanyReqDTO;
import com.ninetosix.adminmodule.nts.dto.CompanyResDTO;
import com.ninetosix.adminmodule.nts.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("company")
    public ResponseEntity<Void> createCompany(@RequestBody CompanyReqDTO reqDTO) {
        companyService.createCompany(reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @GetMapping ("company")
    public ResponseEntity<List<CompanyResDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @GetMapping ("company/{id}")
    public ResponseEntity<CompanyResDTO> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PutMapping("company/{id}")
    public ResponseEntity<Void> modifyCompany(@PathVariable Long id, @RequestBody CompanyReqDTO reqDTO) {
        companyService.modifyCompany(id, reqDTO.name());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
