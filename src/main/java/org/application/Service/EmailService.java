package org.application.Service;

import javax.servlet.http.HttpServletRequest;

import org.application.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSenderObj;

	public void sendMail(Users emp,HttpServletRequest request) {

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/activate/"+ emp.getToken();
		SimpleMailMessage obj = new SimpleMailMessage();
		obj.setTo(emp.getEmail());
		obj.setSubject("Activate Account");
		obj.setText("Hello "+emp.getName()+". Click the link below to activate your account\n"+url);
		javaMailSenderObj.send(obj);

	}

}
