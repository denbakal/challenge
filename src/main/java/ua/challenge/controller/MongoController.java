package ua.challenge.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.challenge.entitiy.UserTest;
import ua.challenge.sandbox.cache.MongoDbConfig;


@RestController
public class MongoController {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDbConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private void clickMongo() {
        Query query = new Query(Criteria.where("name").is("Kostian"));
        UserTest test = new UserTest("Kostian", "1");
        mongoOperation.save(test);

        System.out.println(mongoOperation.findOne(query, UserTest.class));
//        System.out.println(mongoOperation.findAll(UserTest.class));
        mongoOperation.remove(test);
    }
}
