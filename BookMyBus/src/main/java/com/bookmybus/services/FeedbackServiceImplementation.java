package com.bookmybus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.exceptions.FeedbackException;
import com.bookmybus.models.Feedback;
import com.bookmybus.repository.FeedbackDAO;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

	@Autowired
	private FeedbackDAO fDao;
	@Override
	public Feedback addFeedBack(Feedback feedback) throws FeedbackException {
		Feedback newFeedback = fDao.save(feedback);
		return newFeedback;
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedbackException {
		Optional<Feedback> feebackOptional = fDao.findById(feedback.getFeedbackId());
		
		if(feebackOptional.isPresent()) {
			Feedback updatedFeedback = fDao.save(feedback);
			return updatedFeedback;
		}else throw new FeedbackException("Invalid Feedback details...!");
	}

	@Override
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException {
		
		return fDao.findById(feedbackId).orElseThrow(()-> new FeedbackException("No feedback found with given feedbackId :"+feedbackId));
	}

	@Override
	public List<Feedback> viewAllFeedBack() throws FeedbackException {
		List<Feedback> feedbacks = fDao.findAll();
		if(feedbacks.size()==0) {
			throw new FeedbackException("No feedback submitted...!");
		}
		return feedbacks;
	}

	@Override
	public Feedback deleteFeedBack(Integer feedbackId) throws FeedbackException {
		Optional<Feedback> feedback = fDao.findById(feedbackId);
		if(feedback.isPresent()) {
			fDao.deleteById(feedbackId);
			return feedback.get();
		}else {
			 throw new FeedbackException("No feedback found with given feedbackId :"+feedbackId);
		}
		
	}

}
