package com.ninetosix.adminmodule.nts.company.dto;

import com.ninetosix.coremodule.entity.Company;

public record CompanyResDTO(long id, String name) {
    public static CompanyResDTO of(Company company) {
        return new CompanyResDTO(company.getId(), company.getName());
    }
}
