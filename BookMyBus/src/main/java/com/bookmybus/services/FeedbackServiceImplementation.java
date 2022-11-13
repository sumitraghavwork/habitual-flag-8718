package com.bookmybus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.access.service.UserService;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.FeedbackException;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Feedback;
import com.bookmybus.models.Reservation;
import com.bookmybus.models.User;
import com.bookmybus.repository.FeedbackDAO;
import com.bookmybus.repository.ReservationDao;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

	@Autowired
	private FeedbackDAO fDao;
	@Autowired
	private UserService userService;

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Feedback updateFeedBack(Feedback feedback, String key, Integer feedbackId)
			throws FeedbackException, LoginException, UserException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		User user = userService.findByUserLoginId(loggedInUser.getUserId());

		Optional<Feedback> feedbackOptional = fDao.findById(feedback.getFeedbackId());

		if (feedbackOptional.isPresent()) {
			
			Feedback feedbackUser = feedbackOptional.get();
					
			if (feedbackUser.getUser().getUserLoginId() != user.getUserLoginId())
				throw new UserException("Invalid User key Entered");
			
			Feedback updatedFeedback = fDao.save(feedback);

			return updatedFeedback;
		} else
			throw new FeedbackException("Invalid Feedback details...!");
	}

	@Override
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException {

		return fDao.findById(feedbackId)
				.orElseThrow(() -> new FeedbackException("No feedback found with given feedbackId :" + feedbackId));
	}

	@Override
	public List<Feedback> viewAllFeedBack() throws FeedbackException {
		List<Feedback> feedbacks = fDao.findAll();
		if (feedbacks.size() == 0) {
			throw new FeedbackException("No feedback submitted...!");
		}
		return feedbacks;
	}

	@Override
	public Feedback deleteFeedBack(String key, Integer feedbackId) throws FeedbackException, LoginException, UserException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		User user = userService.findByUserLoginId(loggedInUser.getUserId());

		Optional<Feedback> feedback = fDao.findById(feedbackId);
		if (feedback.isPresent()) {
			Feedback existingfeedback = feedback.get();

			User feedbackUser = userService.findByUserLoginId(loggedInUser.getUserId());

			if (feedbackUser.getUserLoginId() == null)	throw new UserException("Invalid User key Entered");

			existingfeedback.setUser(null);
			existingfeedback.setReservation(null);

			fDao.delete(existingfeedback);
			return existingfeedback;
		} else {
			throw new FeedbackException("No feedback found with given feedbackId :" + feedbackId);
		}

	}

	@Override
	public Feedback addFeedBack(Feedback feedback, String key, Integer reservationId)
			throws FeedbackException, UserException, ReservationException {
		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("user not logged in");
		}

		Optional<Reservation> existingReservation = reservationDao.findById(reservationId);

		if (existingReservation.isPresent() == false)
			throw new ReservationException("No reservations found!");

		Reservation res = existingReservation.get();
		User user = userService.findByUserLoginId(loggedInUser.getUserId());
		feedback.setUser(user);
		feedback.setReservation(res);

		Feedback newFeedback = fDao.save(feedback);
		return newFeedback;

	}

}
