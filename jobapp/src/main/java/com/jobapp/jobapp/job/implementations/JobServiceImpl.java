package com.jobapp.jobapp.job.implementations;

import com.jobapp.jobapp.job.entities.Job;
import com.jobapp.jobapp.job.repositories.JobRepository;
import com.jobapp.jobapp.job.services.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service Implementation: Ccontains logic
@Service
public class JobServiceImpl implements JobService {
    // Repository, before: private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            optionalJob.get().setTitle(updatedJob.getTitle());
            optionalJob.get().setMinSalary(updatedJob.getMinSalary());
            optionalJob.get().setMaxSalary(updatedJob.getMaxSalary());
            optionalJob.get().setDescription(updatedJob.getDescription());
            optionalJob.get().setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
