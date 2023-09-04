package com.ninetosix.adminmodule.nts.service;

import com.ninetosix.adminmodule.nts.dto.location.LocationReqDTO;
import com.ninetosix.adminmodule.nts.dto.location.LocationResDTO;
import com.ninetosix.coremodule.entity.Location;
import com.ninetosix.coremodule.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;

    public void createLocation(LocationReqDTO reqDTO) {
        locationRepository.save(Location.create(reqDTO.name(), reqDTO.latitude(), reqDTO.longitude()));
    }

    public List<LocationResDTO> getLocations() {
        return locationRepository.findAll().stream()
                .map(LocationResDTO::of)
                .collect(Collectors.toList());
    }

    public void modifyLocation(long id, LocationReqDTO reqDTO) {
        Location location = locationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        location.modify(reqDTO.name(), reqDTO.latitude(), reqDTO.longitude());
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }
}
