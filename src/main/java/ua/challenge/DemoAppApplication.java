package ua.challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import ua.challenge.sandbox.cache.BookRepository;
import ua.challenge.sandbox.cache.SimpleBookRepository;

@SpringBootApplication
//@EnableMongoRepositories
@EnableAspectJAutoProxy
@EnableAsync
public class DemoAppApplication {
    // todo maybe remove
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }

    @Bean
    public BookRepository bookRepository() {
        return new SimpleBookRepository();
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername("denbakal7@gmail.com");
        mailSender.setPassword("slhsiuetefzduggb");

        mailSender.getJavaMailProperties().put("mail.smtp.host", "smtp.gmail.com");
        mailSender.getJavaMailProperties().put("mail.smtp.port", "587");
        mailSender.getJavaMailProperties().put("mail.transport.protocol", "smtp");
        mailSender.getJavaMailProperties().put("mail.smtp.auth", true);
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
        mailSender.getJavaMailProperties().put("mail.debug", true);
        return mailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }
}
