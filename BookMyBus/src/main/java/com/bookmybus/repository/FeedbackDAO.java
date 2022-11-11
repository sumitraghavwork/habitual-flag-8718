package com.bookmybus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.models.Feedback;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Integer> {

	
}
