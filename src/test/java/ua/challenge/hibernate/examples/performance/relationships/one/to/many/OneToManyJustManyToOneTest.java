package ua.challenge.hibernate.examples.performance.relationships.one.to.many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.challenge.core.sql.tracker.AssertSqlCount.*;
import static ua.challenge.core.sql.tracker.AssertSqlCount.assertUpdateCount;

/**
 * Created by d.bakal on 30.04.2017.
 */
public class OneToManyJustManyToOneTest extends JPAUnitTestCase {
    @Before
    public void insertPosts() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("First post");
            entityManager.persist(post);
        });
    }

    @Test
    public void persistCommentsAndFetchThem() {
        assertInsertCount(1);
        reset();
        doInJPA(entityManager -> {
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);

            PostComment firstComment = new PostComment();
            firstComment.setReview("First Comment");
            firstComment.setPost(post);
            entityManager.persist(firstComment);

            PostComment selectComment = new PostComment();
            selectComment.setReview("Second Comment");
            selectComment.setPost(post);
            entityManager.persist(selectComment);
        });
        assertInsertCount(2);

        reset();
        doInJPA(entityManager -> {
            List<PostComment> comments = entityManager.createQuery(
                    "select pc " +
                            "from ua.challenge.hibernate.examples.performance.relationships.one.to.many.OneToManyJustManyToOneTest$PostComment pc " +
                            "where pc.post.id = :postId", PostComment.class)
                    .setParameter("postId", 1L)
                    .getResultList();
            assertThat(comments.size()).isEqualTo(2);
        });
        assertSelectCount(1);
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Post.class,
                PostComment.class
        };
    }

    @Entity
    @Table(name = "posts")
    @Getter
    @Setter
    @NoArgsConstructor
    static class Post {
        @Id
        @GeneratedValue
        private Long id;

        private String title;
    }

    @Entity
    @Table(name = "post_comments")
    @Getter
    @Setter
    static class PostComment {
        @Id
        @GeneratedValue
        private Long id;

        private String review;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id")
        private Post post;

        PostComment() {
        }

        PostComment(String review) {
            this.review = review;
        }
    }
}
