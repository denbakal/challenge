package ua.challenge.service;

import org.springframework.mail.SimpleMailMessage;

import java.nio.file.Path;

/**
 * Created by d.bakal on 13.11.2016.
 */
public interface EmailSenderService {
    void sendEmail(String to, String subject, String body);
    void sendEmail(SimpleMailMessage message);
    void sendEmailWithAttachment(Path attachment);
}
