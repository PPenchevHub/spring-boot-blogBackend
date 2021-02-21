package com.blog.blog.controllers;

import com.blog.blog.dto.PostDto;
import com.blog.blog.model.Post;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200"})
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        System.out.println(postDto.getUsername());
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getSinglePost(@PathVariable @RequestBody Long id){
        return new ResponseEntity(postService.readSinglePost(id), HttpStatus.OK);
    }

}
