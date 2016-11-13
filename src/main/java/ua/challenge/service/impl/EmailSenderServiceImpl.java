package ua.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ua.challenge.service.EmailSenderService;

import java.nio.file.Path;

/**
 * Created by d.bakal on 13.11.2016.
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(Path attachment) {
    }
}
