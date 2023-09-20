package com.ninetosix.adminmodule.nts.location.dto;

import com.ninetosix.adminmodule.nts.company.dto.CompanyResDTO;

import java.util.List;

public record LocationResDTO(long id, String name, float latitude, float longitude, List<CompanyResDTO> companyList) {
}
