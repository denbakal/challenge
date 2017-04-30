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
 * Created by d.bakal on 30.04.2017.
 */
public class OneToManyBidirectionalTest extends JPAUnitTestCase {
    @Before
    public void insertPosts() {
        doInJPA(entityManager -> {
            Post post = new Post();
            post.setTitle("First post");

            post.addComment(
                    new PostComment("My first review")
            );
            post.addComment(
                    new PostComment("My second review")
            );
            post.addComment(
                    new PostComment("My third review")
            );

            entityManager.persist(post);
        });
    }

    @Test
    public void persist() {
        assertInsertCount(4);
        assertUpdateCount(0);
    }

    @Test
    public void remove() {
        reset();
        doInJPA(entityManager -> {
            Post post = entityManager.find(Post.class, 1L);
            PostComment comment = post.getComments().get(0);
            post.removeComment(comment);
        });
        assertSelectCount(2);
        assertUpdateCount(0);
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

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PostComment> comments = new ArrayList<>();

        public void addComment(PostComment comment) {
            comments.add(comment);
            comment.setPost(this);
        }

        public void removeComment(PostComment comment) {
            comments.remove(comment);
            comment.setPost(null);
        }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PostComment )) return false;
            return id != null && id.equals(((PostComment) o).id);
        }

        @Override
        public int hashCode() {
            return 31;
        }
    }
}
