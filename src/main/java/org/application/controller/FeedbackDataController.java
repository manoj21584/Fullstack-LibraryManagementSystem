package org.application.Controller;

import java.util.List;

import org.application.Entity.Feedback;
import org.application.Repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackDataController {

	@Autowired
	private FeedbackRepo feedbackRepo;

	@PostMapping("/saveFeedback")
	public Feedback saveFeedback(@RequestBody Feedback feed) {
		return feedbackRepo.save(feed);
	}

	@GetMapping("/allFeedback")
	public List<Feedback> getAllFeedback() {
		return feedbackRepo.findAll();
	}

	@DeleteMapping("/feedback/{id}")
	public String deleteFeedback(@PathVariable("id") Integer id) {
		feedbackRepo.deleteById(id);
		return "Employee Deleted";
	}
}
