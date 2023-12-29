package org.application.Service;

import javax.servlet.http.HttpServletRequest;

import org.application.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceOTP {
    @Autowired
	private JavaMailSender javaMailSenderObj;

	public void sendMail(Users foundUser ,HttpServletRequest request) {

        String otp=foundUser.getOtp();
		SimpleMailMessage obj = new SimpleMailMessage();
		obj.setTo(foundUser.getEmail());
		obj.setSubject("Reset password OTP");
		obj.setText(otp);
		javaMailSenderObj.send(obj);
		System.out.println("OPT SENT SUCESSFULLY");

	}
}
