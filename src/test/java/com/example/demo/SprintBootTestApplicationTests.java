package com.example.demo;

import com.example.demo.post.Post;
import com.example.demo.post.PostController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Set;

/**
 * Przed uruchomieniem projektu należy uruchomić bazę danych MySQL z obrazu Dockera loving_swartz
 */
@SpringBootTest
class SprintBootTestApplicationTests {

    @Autowired
    private PostController postController;

    final String POST_TITLE = "qui est esse";

    @Test
    void testGetAllAccounts() {
        ResponseEntity<Set<Post>> accounts = postController.getAllPosts();
        Assertions.assertNotNull(accounts, "Accounts - jest puste");
        Assertions.assertTrue(Objects.requireNonNull(accounts.getBody()).size() >= 2, "Accounts - nie ma 2 lub więcej elementów");
        Assertions.assertTrue(accounts.getBody().stream().anyMatch(post -> post.getTitle().contains(POST_TITLE)), "Accounts - nie zawiera Test 2");
    }

    @Test
    void testGetAccount() {
        ResponseEntity<Post> post = postController.getPostByTitle(POST_TITLE);
        Assertions.assertNotNull(post, "Post - jest puste");
        Assertions.assertEquals(HttpStatusCode.valueOf(200), post.getStatusCode(), "Post - nie ma statusu 200");
        Assertions.assertNotNull(post.getBody(), "Post - nie ma ciała");
        Assertions.assertTrue(Objects.requireNonNull(post.getBody()).getTitle().contains(POST_TITLE), "Pole nazwa w Post - nie zawiera " + POST_TITLE);
    }

}
