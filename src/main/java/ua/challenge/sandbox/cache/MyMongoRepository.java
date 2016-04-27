package ua.challenge.sandbox.cache;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.challenge.entitiy.UserTest;


@Repository
public interface MyMongoRepository extends MongoRepository<UserTest, String> {
}
