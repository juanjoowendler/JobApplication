package com.jobapp.jobapp.company.services;


import com.jobapp.jobapp.company.entities.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findById(Long id);
    void createCompany(Company company);
    boolean updateCompany(Long id, Company updatedCompany);
    boolean deleteCompany(Long id);
}
