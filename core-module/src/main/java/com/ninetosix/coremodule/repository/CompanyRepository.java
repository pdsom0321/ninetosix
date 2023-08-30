package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
