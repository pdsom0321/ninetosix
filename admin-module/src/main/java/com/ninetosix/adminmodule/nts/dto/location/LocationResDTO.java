package com.ninetosix.adminmodule.nts.dto.location;

import com.ninetosix.coremodule.entity.Location;

public record LocationResDTO(long id, String name, float latitude, float longitude) {
    public static LocationResDTO of(Location location) {
        return new LocationResDTO(location.getId(), location.getName(), location.getLatitude(), location.getLongitude());
    }
}
