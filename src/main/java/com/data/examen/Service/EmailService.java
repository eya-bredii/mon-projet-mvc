package com.data.examen.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service 
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;

    public void sendAccountCreationEmail(String toEmail, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Création de votre compte bancaire");
        message.setText("Bonjour " + userName + ",\n\nVotre compte a été créé avec succès dans notre banque.\n\nMerci pour votre confiance.\n\nCordialement,\nL'équipe de la banque.");
        message.setFrom("eyabredai123@gmail.com");

        mailSender.send(message);
    }

}
