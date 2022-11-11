package com.bookmybus.services;

import java.util.List;

import com.bookmybus.exceptions.FeedbackException;
import com.bookmybus.models.Feedback;


public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback) throws FeedbackException;
	
	public Feedback updateFeedBack(Feedback feedback) throws FeedbackException;
	
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> viewAllFeedBack()throws FeedbackException;
	
	public Feedback deleteFeedBack(Integer feedbackId) throws FeedbackException;
}
