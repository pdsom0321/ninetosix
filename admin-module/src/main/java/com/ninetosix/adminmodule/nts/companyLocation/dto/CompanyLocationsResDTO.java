package com.ninetosix.adminmodule.nts.companyLocation.dto;

import com.ninetosix.coremodule.entity.CompanyLocation;

public record CompanyLocationsResDTO(long id, String locationName, float latitude, float longitude) {
    public static CompanyLocationsResDTO of(CompanyLocation companyLocation) {
        return new CompanyLocationsResDTO(companyLocation.getId(), companyLocation.getLocation().getName(), companyLocation.getLocation().getLatitude(), companyLocation.getLocation().getLongitude());
    }
}
