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

/**
 * Created by d.bakal on 22.04.2017.
 */
@Log4j2
public class OneToOneBidirectionalTest extends JPAUnitTestCase {
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
    public void BothLazyWithAdditionalQueryOppositeSide() {
        /* opposite side */
        doInJPA(entityManager -> {
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(4);
        });
    }

    @Test
    public void BothLazyOwnerSide() {
        /* owner side */
        doInJPA(entityManager -> {
            PostDetails postDetails = entityManager.find(PostDetails.class, 2L);
            assertSelectCount(3);
            Post post = postDetails.getPost();
            log.debug("Current post: " + post.getTitle());
            assertSelectCount(5);
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

        @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
        private PostDetails details;

        public void setDetails(PostDetails details) {
            this.details = details;
            details.setPost(this);
        }
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

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id")
        private Post post;
    }
}
