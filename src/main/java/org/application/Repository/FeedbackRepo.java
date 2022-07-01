package org.application.Repository;

import org.application.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}
