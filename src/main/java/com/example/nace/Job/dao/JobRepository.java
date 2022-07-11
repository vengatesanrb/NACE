package com.example.nace.Job.dao;

import com.example.nace.Job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
