package ua.challenge.hibernate.examples.performance.relationships.one.to.one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.challenge.core.sql.tracker.AssertSqlCount.assertSelectCount;

/**
 * Created by d.bakal on 22.04.2017.
 */
@Log4j2
public class OneToOneEmbeddableTest extends JPAUnitTestCase {
    @Before
    public void insertData() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("New Post");

            PostDetails postDetails = new PostDetails();
            postDetails.setCreatedOn(LocalDate.now());
            postDetails.setCreatedBy("User");
            postDetails.setPost(post);

            entityManager.persist(postDetails);
        });
    }

    @Test
    public void check() {
        doInJPA(entityManager -> {
            PostDetails postDetails = entityManager.find(PostDetails.class, 1L);
            assertSelectCount(2);
            Post post = postDetails.getPost();
            log.debug("Current post: " + post.getTitle());
            assertSelectCount(2);
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                PostDetails.class
        };
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String title;
    }

    @Entity
    @Table(name = "post_details")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDetails {
        @Id
        @GeneratedValue
        private Long id;

        @Column(name = "created_on")
        private LocalDate createdOn;

        @Column(name = "created_by")
        private String createdBy;

        @Embedded
        private Post post;
    }
}
