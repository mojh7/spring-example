/*
package com.example.geo.repository;

import static com.example.geo.domain.StatisticsValue.*;
import static com.example.geo.exception.RequestBodyErrorCode.*;

import com.example.geo.domain.QPost;
import com.example.geo.domain.QPostHashtag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.geo.domain.StatisticsType;
import com.example.geo.domain.StatisticsValue;
import com.example.geo.dto.StatisticsGetRequest;
import com.example.geo.dto.StatisticsGetResponse;
import com.example.geo.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostHashtagRepositoryImpl implements PostHashtagRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StatisticsGetResponse> getCountByCreatedAt(StatisticsGetRequest request) {
        String datetimeFormat = "%Y-%m-%d";
        if (StatisticsType.HOUR == request.getType()) {
            datetimeFormat += "T%H";
        }

        StringTemplate formattedDate = Expressions.stringTemplate(
                "date_format({0}, {1})",
                QPost.post.createdAt,
                datetimeFormat
        );

        return jpaQueryFactory.
                select(Projections.constructor(StatisticsGetResponse.class,
                        formattedDate.as("statisticsAt"),
                        countOrSumByStatisticsValue(request.getValue()).as("count"))
                )
                .from(QPostHashtag.postHashtag)
                .join(QPostHashtag.postHashtag.post, QPost.post)
                .where(
                        QPostHashtag.postHashtag.hashtag.eq(request.getHashtag()),
                        QPost.post.createdAt.goe(request.getStart()),
                        QPost.post.createdAt.loe(request.getEnd())
                )
                .groupBy(formattedDate)
                .orderBy(formattedDate.asc())
                .fetch();
    }

    private NumberExpression<Long> countOrSumByStatisticsValue(StatisticsValue statisticsValue) {
        if (COUNT == statisticsValue) {
            return QPost.post.count();
        } else if (LIKECOUNT == statisticsValue) {
            return QPost.post.likeCount.sum();
        } else if (SHARECOUNT == statisticsValue) {
            return QPost.post.shareCount.sum();
        } else if (VIEWCOUNT == statisticsValue) {
            return QPost.post.viewCount.sum();
        } else {
            throw new CustomException(STATISTICS_STATISTICSVALUE_INVALID);
        }
    }
}
*/
