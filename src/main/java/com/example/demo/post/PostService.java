package com.example.demo.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("C:\\Data\\Java\\SpringTestContainers\\src\\main\\resources\\posts.json"); // Ścieżka do pliku JSON
            Posts posts = objectMapper.readValue(file, Posts.class);
            postRepository.saveAll(posts.posts());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Post> getAllPost() {
        return new HashSet<>(postRepository.findAll());
    }

    Post addPost(Post post) {
        postRepository.save(post);
        return post;
    }

    public Post getPostByTitle(String title) {
        return postRepository.findAll().stream()
                .filter(post -> post.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Post updatePost(Post post) {
        postRepository.save(post);
        return post;
    }
}
