package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.entity.CompanyLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyLocationRepository extends JpaRepository<CompanyLocation, Long> {
    Optional<CompanyLocation> findById(Long id);

    List<CompanyLocation> findByCompany(Company company);
}
