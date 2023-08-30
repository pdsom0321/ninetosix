package com.ninetosix.apimodule.nts.dto.company;

import com.ninetosix.coremodule.entity.Company;

public record CompaniesResDTO(Long id, String name) {
    public static CompaniesResDTO of(Company company) {
        return new CompaniesResDTO(company.getId(), company.getName());
    }
}
