package com.ninetosix.adminmodule.nts.companyLocation.service;

import com.ninetosix.adminmodule.nts.company.service.CompanyService;
import com.ninetosix.adminmodule.nts.companyLocation.dto.CompanyLocationCreateReqDTO;
import com.ninetosix.adminmodule.nts.companyLocation.dto.CompanyLocationsResDTO;
import com.ninetosix.adminmodule.nts.location.service.LocationService;
import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.entity.CompanyLocation;
import com.ninetosix.coremodule.entity.Location;
import com.ninetosix.coremodule.repository.CompanyLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyLocationService {
    private final CompanyLocationRepository companyLocationRepository;
    private final CompanyService companyService;
    private final LocationService locationService;

    public void createCompanyLocation(CompanyLocationCreateReqDTO reqDTO) {
        Company company = getCompany(reqDTO.companyId());
        Location location = locationService.location(reqDTO.locationId());

        companyLocationRepository.save(CompanyLocation.create(company, location));
    }

    public List<CompanyLocationsResDTO> companyLocations(long companyId) {
        Company company = getCompany(companyId);

        return companyLocationRepository.findByCompany(company).stream()
                .map(CompanyLocationsResDTO::of)
                .collect(Collectors.toList());
    }

    public void deleteCompanyLocation(long id) {
        companyLocationRepository.deleteById(id);
    }

    private Company getCompany(long companyId) {
        return companyService.company(companyId);
    }

}
