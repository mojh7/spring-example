/*
package com.example.geo.service;

import com.example.geo.exception.ErrorCode;
import com.example.geo.domain.Post;
import com.example.geo.dto.PostGetResponse;
import com.example.geo.dto.PostSearchCondition;
import com.example.geo.dto.PostSearchRequest;
import com.example.geo.exception.CustomException;
import com.example.geo.repository.PostRepository;
import com.example.geo.util.PostSearchConditionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final SnsService snsService;
    private final PostSearchConditionConverter converter;

    @Transactional
    public PostGetResponse getPost(Long postId) {
        Post post = postRepository.findByIdForUpdate(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        post.increaseViewCount();
        return PostGetResponse.of(post);
    }

    public List<PostGetResponse> getPostList(PostSearchRequest request, Pageable pageable) {
        PostSearchCondition condition = converter.convert(request);
        List<Post> posts = postRepository.search(condition, pageable);
        return posts.stream().map(PostGetResponse::of).toList();
    }

    @Transactional
    public void likePost(Long postId, Member member) {
        Post post = postRepository.findByIdForUpdate(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        snsService.likePost(post.getContentId(), post.getType(), member);

        post.increaseLikeCount();
    }

    @Transactional
    public void sharePost(Long postId, Member member) {
        Post post = postRepository.findByIdForUpdate(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        snsService.sharePost(post.getContentId(), post.getType(), member);

        post.increaseShareCount();
    }

}
*/
