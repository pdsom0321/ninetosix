package com.ninetosix.adminmodule.nts.dto;

import com.ninetosix.coremodule.entity.Company;

public record CompanyResDTO(Long id, String name) {
    public static CompanyResDTO of(Company company) {
        return new CompanyResDTO(company.getId(), company.getName());
    }
}
