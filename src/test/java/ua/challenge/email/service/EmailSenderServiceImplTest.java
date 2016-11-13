package ua.challenge.email.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.challenge.DemoAppApplication;

/**
 * Created by d.bakal on 13.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoAppApplication.class})
public class EmailSenderServiceImplTest {
    @Autowired
    private JavaMailSender mailSender;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendEmail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("denbakal7@gmail.com");
        message.setSubject("Test");
        message.setText("Sample text");

        mailSender.send(message);
    }

    @Test
    public void sendEmailWithAttachment() throws Exception {

    }

}