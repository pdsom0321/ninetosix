package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.nts.dto.company.CompaniesResDTO;
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
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompaniesResDTO> companies() {
        return companyRepository.findAll().stream()
                .map(CompaniesResDTO::of)
                .collect(Collectors.toList());
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
