package com.jobapp.jobapp.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobapp.jobapp.job.entities.Job;
import com.jobapp.jobapp.review.entities.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMPANY_TABLE")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore // avoid infinite loop
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
