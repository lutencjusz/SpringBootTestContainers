package com.example.demo;

import com.example.demo.post.PostController;
import com.example.demo.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Przed uruchomieniem testu nie trzeba uruchamiać bazy danych MySQL z obrazu Dockera loving_swartz
 * Test wykorzystuje TestContainers do uruchomienia bazy danych PostgreSQL i uzupełnienia jej danymi
 * UWAGA: Docker musi być uruchomiony
 */
@SpringBootTest
@Testcontainers
public class SprintBootTestContainerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16:0");

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostController postController;

    @Test
    void testAppByTestContainers() {
        final String POST_TITLE = "qui est esse";
        assertTrue(postgreSQLContainer.isCreated());
        assertTrue(postgreSQLContainer.isRunning());
        postRepository.findAll().forEach(post -> System.out.printf("title (%s): %s%n", post.getId(), post.getTitle()));
        assertTrue(Objects.requireNonNull(postController.getPostByTitle(POST_TITLE).getBody()).getTitle().contains(POST_TITLE));
    }
}
