package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.Message;
import com.FrostMilano.Portfolio.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {



}
