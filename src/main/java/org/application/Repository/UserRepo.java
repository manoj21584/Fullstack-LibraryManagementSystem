package org.application.Repository;

import java.util.Optional;

import org.application.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	Optional<Users> findByToken(String token);

	Users findByEmail(String email);

	Users findByOtp(String otp);

	//@Query("SELECT userId, name FROM Users")
	//List<Users> findAllByUserRoles();
	// save(entity)
	// findAll()
	// findById(primaryKey)
	// delete(entity)
	// deleteById(primaryKey)
}
