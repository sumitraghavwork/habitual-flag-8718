package com.bookmybus.services;

import java.util.List;

import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.exceptions.FeedbackException;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Feedback;


public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback, String key, Integer reservationId) throws FeedbackException, UserException, ReservationException;
	
	public Feedback updateFeedBack(Feedback feedback) throws FeedbackException;
	
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> viewAllFeedBack()throws FeedbackException;
	
	public Feedback deleteFeedBack(Integer feedbackId) throws FeedbackException;
}
