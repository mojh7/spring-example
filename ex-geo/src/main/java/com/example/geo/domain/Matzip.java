package com.example.geo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Matzip extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Embedded
    Location location;

    @Column(nullable = false)
    Long totalRating;

    @Column(nullable = false)
    Long reviewCount;

    @Builder
    private Matzip(String name, Location location, Long totalRating, Long reviewCount) {
        this.name = name;
        this.location = location;
        this.totalRating = totalRating;
        this.reviewCount = reviewCount;
    }

    /**
     * 현재 맛집과의 거리 계산 후 반환
     * @param requestLocation 해당 맛집과 거리 비교할 위치
     * @return 거리
     */
    public double calcDistanceFrom(Location requestLocation) {
        return location.distanceFrom(requestLocation);
    }

}
