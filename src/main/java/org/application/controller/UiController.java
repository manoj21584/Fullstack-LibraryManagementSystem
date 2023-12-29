package org.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/profile")
	public String goToProfile(){
		return "profile";
	}
	@GetMapping("/")
	public String goToHomePage() {
		return "login";
	}

	@GetMapping("/departments")
	public String getDepartment() {
		return "departments";
	}

	@GetMapping("/about")
	public String handleAbout() {
		return "about";
	}

	@GetMapping("/handleDownloads")
	public String handleDocuments() {
		return "handleDownloads";
	}

	@GetMapping("/dashboardforAdmin")
	public String getAdminDashboard() {
		return "dashboardforAdmin";
	}

	@GetMapping("/forgot")
	public String goToForgotPasswordPage1() {
		return "forgot";
	}

	@GetMapping("/reset")
	public String goToResetPasswordPage2() {
		return "reset";
	}

	@GetMapping("/dashboard")
	public String goToDashboard() {
		return "dashboard";
	}

	@GetMapping("/documents")
	public String goToDocuments() {
		return "documents";
	}

	@GetMapping("/feedbackTable")
	public String goToFeedbackTable() {
		return "feedbackTable";
	}

	@GetMapping("/userTable")
	public String goToUserTable() {
		return "userTable";
	}

	@GetMapping("/register")
	public String gotoRegister() {
		return "register";
	}

	@GetMapping("/feedback")
	public String goToFeedback() {
		return "feedback";
	}

	@GetMapping("/downloads")
	public String goToContribute() {
		return "downloads";
	}

	@GetMapping("/notes")
	public String goToNotes() {
		return "notes";
	}

	@GetMapping("/lab")
	public String goToLab() {
		return "lab";
	}

	@GetMapping("/books")
	public String goToBooks() {
		return "books";
	}

	@GetMapping("/CSE")
	public String goToCSE() {
		return "CSE";
	}

	@GetMapping("/engineering")
	public String goToEngineering() {
		return "engineering";
	}

	@GetMapping("/update")
	public String updateUser() {
		return "update";
	}

	@GetMapping("/successPage")
	public String goToSucess() {
		return "successPage";
	}

	@GetMapping("/errorPage")
	public String goToError() {
		return "errorPage";
	}
}
