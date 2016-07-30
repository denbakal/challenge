package ua.challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import ua.challenge.sandbox.cache.BookRepository;
import ua.challenge.sandbox.cache.SimpleBookRepository;

@SpringBootApplication
@EnableMongoRepositories
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

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }
}
