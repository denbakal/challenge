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

import static ua.challenge.core.sql.tracker.AssertSqlCount.assertSelectCount;
import static ua.challenge.core.sql.tracker.AssertSqlCount.reset;

/**
 * Created by d.bakal on 22.04.2017.
 */
@Log4j2
public class OneToOneMapsIdTest extends JPAUnitTestCase {
    @Before
    public void insertData() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("New Post");

            PostDetails postDetails = new PostDetails();
            postDetails.setCreatedOn(LocalDate.now());
            postDetails.setCreatedBy("User");
            postDetails.setPost(post);

            entityManager.persist(post);
            entityManager.persist(postDetails);
        });
    }

    @Test
    public void checkChildEntity() {
        /* opposite side */
        doInJPA(entityManager -> {
            reset();
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);
        });
    }

    @Test
    public void checkParentEntity() {
        /* owner side */
        doInJPA(entityManager -> {
            reset();
            PostDetails postDetails = entityManager.find(PostDetails.class, 1L);
            assertSelectCount(1);
            Post post = postDetails.getPost();
            log.debug("Current post: " + post.getTitle());
            assertSelectCount(2);
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Post.class,
                PostDetails.class
        };
    }

    @Entity
    @Table(name = "post")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @Id
        @GeneratedValue
        private Long id;

        private String title;
    }

    @Entity
    @Table(name = "post_details")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDetails {
        @Id
        private Long id;

        @Column(name = "created_on")
        private LocalDate createdOn;

        @Column(name = "created_by")
        private String createdBy;

        @OneToOne(fetch = FetchType.LAZY)
        @MapsId
        private Post post;
    }
}
