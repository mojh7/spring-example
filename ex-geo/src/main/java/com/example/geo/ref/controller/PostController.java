/*
package com.example.geo.controller;

import com.example.geo.dto.PostGetResponse;
import com.example.geo.security.CurrentMember;
import com.example.geo.dto.PostSearchRequest;
import com.example.geo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts/{postId}")
    public PostGetResponse getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/api/posts")
    public List<PostGetResponse> getPostList(PostSearchRequest request, Pageable pageable) {
        return postService.getPostList(request, pageable);
    }

    @PostMapping("/api/posts/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable("postId") Long postId, @CurrentMember Member member) {
        postService.likePost(postId, member);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/posts/{postId}/share")
    public ResponseEntity<?> sharePost(@PathVariable("postId") Long postId, @CurrentMember Member member) {
        postService.sharePost(postId, member);
        return ResponseEntity.ok().build();
    }

}
*/
