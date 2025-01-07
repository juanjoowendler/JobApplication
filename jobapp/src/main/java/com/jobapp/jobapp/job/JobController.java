package com.jobapp.jobapp.job;


import com.jobapp.jobapp.job.entities.Job;
import com.jobapp.jobapp.job.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller: Contains endpoints
@RestController
@RequestMapping("/jobs") // base url for the entire controller
public class JobController {
    // Services
    private JobService jobService;

    // Constructor
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // GET /jobs: Get all jobs
    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK); // Another way: return  ResponseEntity.ok(jobService.findAll())
    }

    // GET /jobs/{id}: Get a specific job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST /jobs: Creaate a new job (request body should contain the job details)
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    // DELETE /jobs/{id}: Delete a specific job by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJob(id);
        if (!deleted) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }

    // PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job details)
    @PutMapping("/{id}") // Another way: RequestMapping(value = "/jobs/{id}", type = RequestMethos.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
