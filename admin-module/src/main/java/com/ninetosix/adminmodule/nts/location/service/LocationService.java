package com.ninetosix.adminmodule.nts.location.service;

import com.ninetosix.adminmodule.nts.company.dto.CompanyResDTO;
import com.ninetosix.adminmodule.nts.location.dto.LocationReqDTO;
import com.ninetosix.adminmodule.nts.location.dto.LocationResDTO;
import com.ninetosix.coremodule.entity.CompanyLocation;
import com.ninetosix.coremodule.entity.Location;
import com.ninetosix.coremodule.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;

    public void createLocation(LocationReqDTO reqDTO) {
        locationRepository.save(Location.create(reqDTO.name(), reqDTO.latitude(), reqDTO.longitude()));
    }

    public Location location(long id) {
        return locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<LocationResDTO> locations() {
        List<Location> locations = locationRepository.findAll();

        return locations.stream()
                .map(this::getLocationResDTO)
                .collect(Collectors.toList());
    }

    public void modifyLocation(long id, LocationReqDTO reqDTO) {
        Location location = locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        location.modify(reqDTO.name(), reqDTO.latitude(), reqDTO.longitude());
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }

    private LocationResDTO getLocationResDTO(Location location) {
        List<CompanyResDTO> companyList = location.getCompanyLocations().stream()
                .map(CompanyLocation::getCompany)
                .map(CompanyResDTO::of)
                .collect(Collectors.toList());

        return new LocationResDTO(
                location.getId(),
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                companyList
        );
    }
}
