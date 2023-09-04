package com.ninetosix.adminmodule.nts.service;

import com.ninetosix.adminmodule.nts.dto.company.CompanyResDTO;
import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void createCompany(String name) {
        companyRepository.save(Company.create(name));
    }

    public List<CompanyResDTO> getCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyResDTO::of)
                .collect(Collectors.toList());
    }

    public CompanyResDTO getCompany(Long id) {
        return companyRepository.findById(id).map(CompanyResDTO::of).orElseThrow(NoSuchElementException::new);
    }

    public void modifyCompany(Long id, String name) {
        Company company = companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        company.modify(name);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
