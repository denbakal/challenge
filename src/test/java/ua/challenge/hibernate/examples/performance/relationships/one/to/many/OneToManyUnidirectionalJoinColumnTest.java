package ua.challenge.hibernate.examples.performance.relationships.one.to.many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ua.challenge.core.sql.tracker.AssertSqlCount.*;

/**
 * Created by d.bakal on 29.04.2017.
 */
public class OneToManyUnidirectionalJoinColumnTest extends JPAUnitTestCase {
    @Before
    public void insertPosts() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("First post");

            post.getComments().add(
                    new PostComment("My first review")
            );
            post.getComments().add(
                    new PostComment("My second review")
            );
            post.getComments().add(
                    new PostComment("My third review")
            );

            entityManager.persist(post);
        });
    }

    @Test
    public void persist() {
        assertInsertCount(4);
        assertUpdateCount(3);
    }

    @Test
    public void remove() {
        reset();
        doInJPA(entityManager -> {
            Post post = entityManager.find(Post.class, 1L);
            post.getComments().remove(0);
        });
        assertSelectCount(2);
        assertUpdateCount(1);
        assertDeleteCount(1);
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

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "post_id")
        private List<PostComment> comments = new ArrayList<>();
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

        PostComment() {
        }

        PostComment(String review) {
            this.review = review;
        }
    }
}
