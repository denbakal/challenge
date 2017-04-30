package ua.challenge.hibernate.examples.performance.orphan.removal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.challenge.core.sql.tracker.AssertSqlCount.*;

/**
 * Created by d.bakal on 29.04.2017.
 */
public class OrphanRemovalTest extends JPAUnitTestCase {
    @Before
    public void insertData() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("First Post");

            post.getComments().add(
                    new PostComment("First Comment")
            );

            post.getComments().add(
                    new PostComment("Second Comment")
            );

            post.getComments().add(
                    new PostComment("Third Comment")
            );

            entityManager.persist(post);
        });
        assertInsertCount(4);
    }

    @Test(expected = RollbackException.class)
    public void exceptionCollectionWithCascadeAllDeleteOrphan() {
        doInJPA(entityManager -> {
            reset();
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);

            post.setComments(new ArrayList<>());
        });
    }

    @Test
    public void avoidExceptionCollectionWithCascadeAllDeleteOrphan() {
        doInJPA(entityManager -> {
            reset();
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);

            // So generally speaking, just clear out the old collection,
            // rather than de-referencing it and creating a new one.
            post.getComments().clear();
        });
    }

    @Test
    public void deleteWithCascadeAllWithoutOrphanRemoval() {
        doInJPA(entityManager -> {
            reset();
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);

            List<PostComment> comments = post.getComments();
            assertThat(comments.size()).isEqualTo(3);
            assertSelectCount(2);

            PostComment comment = post.getComments().get(0);
            post.getComments().remove(comment);
        });
        assertUpdateCount(1);
    }

    @Test
    public void deleteWithCascadeAllAndOrphanRemoval() {
        doInJPA(entityManager -> {
            reset();
            Post post = entityManager.find(Post.class, 1L);
            assertSelectCount(1);

            List<PostComment> comments = post.getComments();
            assertThat(comments.size()).isEqualTo(3);
            assertSelectCount(2);

            PostComment comment = post.getComments().get(0);
            post.getComments().remove(comment);
        });
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

//        @OneToMany(cascade = CascadeType.ALL)
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

        private String comment;

        public PostComment() {
        }

        public PostComment(String comment) {
            this.comment = comment;
        }
    }
}
