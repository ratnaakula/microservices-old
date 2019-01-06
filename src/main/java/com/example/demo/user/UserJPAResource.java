package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID: " + id);
        }
        return user.get();
    }

    @PostMapping (path="/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path="/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id) {
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID: " + id);
        }
        return user.get().getPosts();
    }

    @PostMapping(path="/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> optionaluser =  userRepository.findById(id);
        if (!optionaluser.isPresent()) {
            throw new UserNotFoundException("ID: " + id);
        }
        User user = optionaluser.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
