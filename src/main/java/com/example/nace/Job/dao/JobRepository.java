package com.example.nace.Job.dao;

import com.example.nace.Job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
