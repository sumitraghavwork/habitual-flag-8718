package com.bookmybus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.exceptions.FeedbackException;
import com.bookmybus.models.Feedback;
import com.bookmybus.services.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/feedbacks")
	public ResponseEntity<Feedback> addFeedbackHandler(@RequestBody Feedback feedback) throws FeedbackException {
		
		Feedback addFeedback = feedbackService.addFeedBack(feedback);		
		
		return new ResponseEntity<Feedback>(addFeedback, HttpStatus.OK);
	}
	
	@PutMapping("/feedbacks")
	public ResponseEntity<Feedback> updateFeedbackHandler(@RequestBody Feedback feedback) throws FeedbackException {		
		
		Feedback updatedFeedback= feedbackService.updateFeedBack(feedback);
				
		return new ResponseEntity<Feedback>(updatedFeedback,HttpStatus.OK);
		
	}
	
	@GetMapping("/feedbacks/{feedbackId}")
	public ResponseEntity<Feedback> viewFeedBackHandler(@PathVariable Integer feedbackId) throws FeedbackException{
		
		Feedback feedback = feedbackService.viewFeedBack(feedbackId);
		
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
		
	}
	
	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> viewAllFeedBackHandler() throws FeedbackException{
		
		List<Feedback> feedbackList = feedbackService.viewAllFeedBack();
		
		return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/feedbacks/{feedbackId}")
	public ResponseEntity<Feedback> deleteFeedbackHandler(@PathVariable Integer feedbackId) throws FeedbackException{
		Feedback deleteFeedback = feedbackService.deleteFeedBack(feedbackId);
		
		return new ResponseEntity<Feedback>(deleteFeedback, HttpStatus.ACCEPTED);
		
	}
}
