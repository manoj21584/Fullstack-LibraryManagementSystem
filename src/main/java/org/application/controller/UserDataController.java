package org.application.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.application.Entity.Users;
import org.application.Repository.UserRepo;
import org.application.Service.EmailService;
import org.application.Service.EmailServiceOTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	EmailServiceOTP otpEmailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	private void encodePassword(Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));

	}

	@PostMapping("/saveEmployee")
	public Users saveEMployee(@RequestBody Users users, HttpServletRequest request) throws Exception {
		String tempEmailId = users.getEmail();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			Users userMailObj = userRepo.findByEmail(tempEmailId);
			if (userMailObj == null) {
				users.setIsActive(false);
				users.setToken(UUID.randomUUID().toString());
				if((users.getEmail()).equals("manojraj310@gmail.com")){
					users.setRole("CREATER");
				}
				else{
					users.setRole("USER");
				}
				users.setMyTheme("BRIGHT");
				encodePassword(users);
				emailService.sendMail(users, request);
			} else {
				throw new Exception("User with " + tempEmailId + " is already exist");
			}
		}
		return userRepo.save(users);
	}

	@PostMapping("/adminLogin")
	public void isAdmin(@RequestBody Users users, HttpServletRequest request) throws Exception {
		String tempEmailId = "rohit821128@gmail.com";

		Users Admin = userRepo.findByEmail(tempEmailId);
		if (!passwordEncoder.matches(users.getPassword(), Admin.getPassword())) {
			
			throw new Exception("***************Incorrect Password*******************");
			
		}
	}

	@GetMapping("/all")
	public List<Users> getAllEmployee() {
		return userRepo.findAll();
	}
	// @GetMapping("/userWithRole")
	// public List<Users> getUserWithRole() {
	// 	return userRepo.findAllByUserRoles();
	// }

	@GetMapping("/employee/{userId}")
	public Users getEmployee(@PathVariable("userId") Integer userId) {
		return userRepo.findById(userId).get();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/user/{email}")
	public Users getThisUserInfo(@PathVariable("email") String email) {
		return userRepo.findByEmail(email);
	}
	@DeleteMapping("/user/{email}")
	public String deleteThisUserProfile(@PathVariable("email") String email) {
		Users userFound = userRepo.findByEmail(email);
		int userIdFound=userFound.getUserId();
		userRepo.deleteById(userIdFound);
		return "Employee Deleted";
	}
	@PostMapping("/user/{email}")
	public Users updateThisUserProfile(@PathVariable("email") String email, @RequestBody Users users) {
		Users userFound = userRepo.findByEmail(email);
		userFound.setName(users.getName());
		userFound.setContact(users.getContact());
		userFound.setDepartment(users.getDepartment());
		return userRepo.save(userFound);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@DeleteMapping("/employee/{userId}")
	public String deleteEmployee(@PathVariable("userId") Integer userId) {
		userRepo.deleteById(userId);
		return "Employee Deleted";
	}

	@PostMapping("/employee/{userId}")
	public Users updateEmployee(@PathVariable("userId") Integer userId, @RequestBody Users users) {
		Users userFound = userRepo.findById(userId).get();
		userFound.setContact(users.getContact());
		userFound.setName(users.getName());
		return userRepo.save(userFound);
	}
	@PostMapping("/updateRole/{userId}")
	public Users SaveNewRole(@PathVariable("userId") Integer userId, @RequestBody Users users) {
		Users userFound = userRepo.findById(userId).get();
		userFound.setRole(users.getRole());
		return userRepo.save(userFound);
	}

	@GetMapping("/activate/{token}")
	public String getsuccessPage(@PathVariable("token") String token) {
		String result="";
		Optional<Users> empObj = userRepo.findByToken(token);
		if (empObj.isPresent()) {
			Users obj = empObj.get();
			obj.setIsActive(true);
			obj.setToken(null);
			userRepo.save(obj);
			//return "<h1 align='center'>Activated Sucessfully</h1><br/><h3 align='center'>You can close this window</h3>";
			result="<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "<meta charset=\"ISO-8859-1\">\n"
					+ "<title>OneNow | Success</title>\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\"\n"
					+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" />\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\"\n"
					+ "	href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" />\n"
					+ "<style type=\"text/css\">\n"
					+ "body {\n"
					+ "	background: #f2f2f2;\n"
					+ "}\n"
					+ "\n"
					+ ".payment {\n"
					+ "	border: 1px solid #f2f2f2;\n"
					+ "	height: 280px;\n"
					+ "	border-radius: 20px;\n"
					+ "	background: #fff;\n"
					+ "}\n"
					+ "\n"
					+ ".payment_header {\n"
					+ "	background: rgba(255, 102, 0, 1);\n"
					+ "	padding: 20px;\n"
					+ "	border-radius: 20px 20px 0px 0px;\n"
					+ "}\n"
					+ "\n"
					+ ".check {\n"
					+ "	margin: 0px auto;\n"
					+ "	width: 50px;\n"
					+ "	height: 50px;\n"
					+ "	border-radius: 100%;\n"
					+ "	background: #fff;\n"
					+ "	text-align: center;\n"
					+ "}\n"
					+ "\n"
					+ ".check i {\n"
					+ "	vertical-align: middle;\n"
					+ "	line-height: 50px;\n"
					+ "	font-size: 30px;\n"
					+ "}\n"
					+ "\n"
					+ ".content {\n"
					+ "	text-align: center;\n"
					+ "}\n"
					+ "\n"
					+ ".content  h1 {\n"
					+ "	font-size: 25px;\n"
					+ "	padding-top: 25px;\n"
					+ "}\n"
					+ "\n"
					+ ".content a {\n"
					+ "	width: 200px;\n"
					+ "	height: 35px;\n"
					+ "	color: #fff;\n"
					+ "	border-radius: 30px;\n"
					+ "	padding: 5px 10px;\n"
					+ "	background: rgba(255, 102, 0, 1);\n"
					+ "	transition: all ease-in-out 0.3s;\n"
					+ "}\n"
					+ "\n"
					+ ".content a:hover {\n"
					+ "	text-decoration: none;\n"
					+ "	background: #000;\n"
					+ "}\n"
					+ "</style>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "	<div class=\"container\">\n"
					+ "		<div class=\"row\">\n"
					+ "			<div class=\"col-md-6 mx-auto mt-5\">\n"
					+ "				<div class=\"payment\">\n"
					+ "					<div class=\"payment_header\">\n"
					+ "						<div class=\"check\">\n"
					+ "							<i class=\"fa fa-check\" aria-hidden=\"true\"></i>\n"
					+ "						</div>\n"
					+ "					</div>\n"
					+ "					<div class=\"content\">\n"
					+ "						<h1>Successfully Activated !</h1>\n"
					+ "						<br />\n"
					+ "						<br /> <a href=\"/login\">LOGIN</a>\n"
					+ "					</div>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "		</div>\n"
					+ "	</div>\n"
					+ "</body>\n"
					+ "</html>\n"
					+ "";
		}
		else
		{
			//return "<h1 align='center'>Oops! This Page Could Not Be Found</h1>";
			result="<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "<meta charset=\"ISO-8859-1\">\n"
					+ "<title>OneNow | Error</title>\n"
					+ "<style>\n"
					+ ".error {\n"
					+ "	margin-left: 20%;\n"
					+ "}\n"
					+ "\n"
					+ ".error h1 {\n"
					+ "	font-size: 100px;\n"
					+ "	color: red;\n"
					+ "}\n"
					+ "\n"
					+ ".universalButton {\n"
					+ "	color: black;\n"
					+ "	border: 1px solid BLACK;\n"
					+ "	padding: 5px 20px;\n"
					+ "	margin-top: 15px;\n"
					+ "	border-radius: 5px;\n"
					+ "	text-decoration: none;\n"
					+ "	font-size: 16px;\n"
					+ "}\n"
					+ "\n"
					+ ".universalButton:hover {\n"
					+ "	background-color: black;\n"
					+ "	color: gainsboro;\n"
					+ "}\n"
					+ "</style>\n"
					+ "\n"
					+ "</head>\n"
					+ "\n"
					+ "<body>\n"
					+ "	<div class=\"error\">\n"
					+ "		<h1>404</h1>\n"
					+ "		<h2>Oops! This Page Could Not Be Found</h2>\n"
					+ "		<p>Sorry but the page you are looking for does not exist, have\n"
					+ "			been removed. name changed or is temporarily unavailable</p>\n"
					+ "		<a href=\"/login\"><button class=\"universalButton\">RETRY</button></a>\n"
					+ "	</div>\n"
					+ "</body>\n"
					+ "\n"
					+ "</html>";
		}
		return result;
	}

	@PostMapping("/reset/{emailid}")
	public Users resetPasswordPage1(@PathVariable("emailid") String email, HttpServletRequest request) throws Exception {
		Users userFound = userRepo.findByEmail(email);
		if (userFound == null) {
			throw new Exception("User with " + email + " does not exist");
		} else {
			Random rnd = new Random();
			int randomOtp = rnd.nextInt(999999);
			userFound.setOtp(String.valueOf(randomOtp));
			otpEmailService.sendMail(userFound, request);
		}
		return userRepo.save(userFound);
	}

	@PostMapping("/updatePassword")
	public Users updatePassword(@RequestBody Users users, HttpServletRequest request) throws Exception {
		Users foundUser = userRepo.findByOtp(users.getOtp());
		if (foundUser != null) {
			foundUser.setPassword(users.getPassword());
			encodePassword(foundUser);
			foundUser.setOtp(null);
		} else {
			throw new Exception("given OTP : " + users.getOtp() + " is invalid");
		}
		return userRepo.save(foundUser);
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/theme/{email}")
	public Users getThisUsersTheme(@PathVariable("email") String email) {
		return userRepo.findByEmail(email);
	}
	@PostMapping("/theme/{email}")
	public Users changeThisUsersTheme(@PathVariable("email") String email, @RequestBody Users users) {
		Users userFound = userRepo.findByEmail(email);
		userFound.setMyTheme(users.getMyTheme());
		return userRepo.save(userFound);
	}
}
