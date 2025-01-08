package com.jobapp.jobapp.company.repositories;

import com.jobapp.jobapp.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
