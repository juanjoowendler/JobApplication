package com.jobapp.jobapp.job.services;

import com.jobapp.jobapp.job.entities.Job;

import java.util.List;

// Interface: Contains methods
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long id);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job updatedJob);
}
