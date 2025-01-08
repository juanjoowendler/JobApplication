package com.jobapp.jobapp.job.repositories;

import com.jobapp.jobapp.job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
