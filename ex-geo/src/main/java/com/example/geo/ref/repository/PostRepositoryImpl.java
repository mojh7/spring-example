/*
package com.example.geo.repository;

import com.example.geo.domain.QPost;
import com.example.geo.domain.QPostHashtag;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.geo.domain.Post;
import com.example.geo.domain.SearchByType;
import com.example.geo.domain.SnsType;
import com.example.geo.dto.PostSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> search(PostSearchCondition condition, Pageable pageable) {
        JPAQuery<Post> query = queryFactory
                .selectFrom(QPost.post)
                .join(QPost.post.postHashtags, QPostHashtag.postHashtag)
                .on(
                        QPostHashtag.postHashtag.hashtag.eq(condition.getHashtag())
                )
                .where(
                        typeEqual(condition.getType()),
                        searchContain(condition.getSearchBy(), condition.getSearch())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<Post> pathBuilder = new PathBuilder<>(QPost.post.getType(), QPost.post.getMetadata());
            query.orderBy(
                    new OrderSpecifier(
                            o.isAscending() ? Order.ASC : Order.DESC,
                            pathBuilder.get(o.getProperty())
                    )
            );
        }
        return query.fetch();
    }

    private BooleanExpression typeEqual(SnsType type) {
        return type == null ? null : QPost.post.type.eq(type);
    }

    private BooleanExpression searchContain(SearchByType searchBy, String search) {
        if (search == null) {
            return null;
        }

        if (searchBy == SearchByType.TITLE) {
            return QPost.post.title.contains(search);
        }

        if (searchBy == SearchByType.CONTENT) {
            return QPost.post.content.contains(search);
        }

        return QPost.post.title.contains(search).or(QPost.post.content.contains(search));
    }

}
*/
