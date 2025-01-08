package com.jobapp.jobapp.company;

import com.jobapp.jobapp.company.entities.Company;
import com.jobapp.jobapp.company.services.CompanyService;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // GET /companies
    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    // GET /companies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST /companies
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    // PUT /companies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean updated = companyService.updateCompany(id, updatedCompany);
        if (updated) {
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    // DELETE / companies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (!deleted) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }

}
