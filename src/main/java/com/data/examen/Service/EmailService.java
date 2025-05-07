package com.data.examen.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service 
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;

    public void sendAccountCreationEmail(String toEmail, String userName,String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail); //le destinataire
        email.setSubject("Création de votre compte bancaire");
        email.setText("Bonjour " + userName + ",\n\nVotre compte a été créé avec succès dans notre banque.\n\nMerci pour votre confiance.\n\nCordialement,\nL'équipe de la banque.");
        email.setFrom("eyabredai123@gmail.com"); //l'expéditeur


        mailSender.send(email);
    }

}
