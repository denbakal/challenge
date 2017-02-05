package ua.challenge.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by d.bakal on 05.02.2017.
 */
@Configuration
@EnableRabbit
@ComponentScan("ua.challenge.messaging.example.topics")
public class RabbitTopicsConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("topics-exchange");
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topics-exchange");
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("topics-1");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("topics-2");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue1()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("lazy.#");
    }

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory listenerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }
}
