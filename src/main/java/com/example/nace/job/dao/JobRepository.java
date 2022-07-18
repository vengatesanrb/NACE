package com.example.nace.job.dao;

import com.example.nace.job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
