package com.example.geo.dto;

import com.example.geo.domain.Matzip;
import lombok.Builder;
import lombok.Getter;

/**
 * 맛집 목록 조회는 상세 조회 대비 요약된 형태로 반환
 */
@Getter
public class MatzipSummaryReponse {

    private String name;

    private double distance;

    double avgRating;

    @Builder
    private MatzipSummaryReponse(String name, double distance, double avgRating) {
        this.name = name;
        this.distance = distance;
        this.avgRating = avgRating;
    }


    public static MatzipSummaryReponse of(Matzip matzip, double distance) {
        return MatzipSummaryReponse.builder()
                                   .name(matzip.getName())
                                   .distance(distance)
                                   .avgRating((double) matzip.getTotalRating() / matzip.getReviewCount())
                                   .build();
    }
}
