package com.ninetosix.adminmodule.nts.companyLocation.dto;

import com.ninetosix.coremodule.entity.CompanyLocation;
import com.ninetosix.coremodule.entity.Location;

public record CompanyLocationsResDTO(long id, String locationName, float latitude, float longitude) {
    public static CompanyLocationsResDTO of(CompanyLocation companyLocation) {
        Location location = companyLocation.getLocation();
        return new CompanyLocationsResDTO(companyLocation.getId(), location.getName(), location.getLatitude(), location.getLongitude());
    }
}
