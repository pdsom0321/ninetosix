package com.ninetosix.adminmodule.nts.company.service;

import com.ninetosix.adminmodule.nts.company.dto.CompanyResDTO;
import com.ninetosix.coremodule.entity.Company;
import com.ninetosix.coremodule.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void createCompany(String name) {
        companyRepository.save(Company.create(name));
    }

    public Company company(long id) {
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<CompanyResDTO> companies() {
        return companyRepository.findAll().stream()
                .map(CompanyResDTO::of)
                .collect(Collectors.toList());
    }

    public void modifyCompany(long id, String name) {
        Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        company.modify(name);
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }
}
