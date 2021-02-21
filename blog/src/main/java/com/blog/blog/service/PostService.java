package com.blog.blog.service;

import com.blog.blog.dto.PostDto;
import com.blog.blog.exceptions.PostNotFoundExceptoion;
import com.blog.blog.model.Post;
import com.blog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    public List<PostDto> showAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    public PostDto mapFromPostToDto(Post post) {
        PostDto postDto =  new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        postDto.setTitle(post.getTitle());
        return postDto;
    }

    public void createPost(PostDto postDto){
        System.out.println(postDto.getUsername());
        Post post =  new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User username =  authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No user loged in"));
        post.setUsername(username.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);

    }

    public PostDto readSinglePost(Long id) {
        Post post =  postRepository.findById(id).orElseThrow(()-> new PostNotFoundExceptoion("No post with this id"));
        return mapFromPostToDto(post);

    }
}
