package ua.challenge.email.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ua.challenge.DemoAppApplication;
import ua.challenge.config.EmailConfigTest;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by d.bakal on 13.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {EmailConfigTest.class})
@AutoConfigureMockMvc(secure=false)
public class EmailSenderServiceImplTest {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

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
    public void sendEmailTextTemplate() throws Exception {
        // Prepare the evaluation context
        Context context = new Context(Locale.getDefault());
        context.setVariable("name", "Denis");
        context.setVariable("subscriptionDate", new Date());
        context.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject("Example TEXT email");
        message.setTo("denbakal7@gmail.com");

        // Create the body using Thymeleaf
        String messageBody = templateEngine.process("text/sample", context);
        message.setText(messageBody);

        // Send mail
        mailSender.send(mimeMessage);
    }

    @Test
    public void sendEmailHtmlTemplate() throws Exception {
        // Prepare the evaluation context
        Context context = new Context(Locale.getDefault());
        context.setVariable("name", "Denis");
        context.setVariable("subscriptionDate", new Date());
        context.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject("Example TEXT email");
        message.setTo("denbakal7@gmail.com");

        // Create the HTML body using Thymeleaf
        String messageBody = templateEngine.process("html/sample", context);
        message.setText(messageBody, true);

        // Send mail
        mailSender.send(mimeMessage);
    }

    @Test
    public void sendEmailWithAttachment() throws Exception {

    }

}