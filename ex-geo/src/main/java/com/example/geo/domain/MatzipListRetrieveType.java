package com.example.geo.domain;

import com.example.geo.dto.MatzipSummaryReponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 다형성 구현 if/else로 검색 로직 구분 피하기 위해 enum 필드의 조회 로직 포함
 * <p>
 * 맛집 검색 목록 조회 시 query parameter로 입력받는 값 중 하나
 */
public enum MatzipListRetrieveType {
    // 거리 오름차순
    DISTANCE((matzipList, location, range) -> matzipList.stream()
                                                        .map(m -> MatzipSummaryReponse.of(m, m.calcDistanceFrom(location)))
                                                        .filter(m -> m.getDistance() <= range)
                                                        .sorted(Comparator.comparingDouble(MatzipSummaryReponse::getDistance))
                                                        .collect(Collectors.toList())),
    // 별점 내림차순
    AVG_RATING((matzipList, location, range) -> matzipList.stream()
                                                          .map(m -> MatzipSummaryReponse.of(m, m.calcDistanceFrom(location)))
                                                          .filter(m -> m.getDistance() <= range)
                                                          .sorted(Comparator.comparingDouble(MatzipSummaryReponse::getAvgRating).reversed())
                                                          .collect(Collectors.toList()));

    private TriFunction<List<Matzip>, Location, Double, List<MatzipSummaryReponse>> retrieveFunc;

    MatzipListRetrieveType(TriFunction<List<Matzip>, Location, Double, List<MatzipSummaryReponse>> retrieveFunc) {
        this.retrieveFunc = retrieveFunc;
    }

    public List<MatzipSummaryReponse> retrieve(List<Matzip> matzipList, Location requestLocation, double range) {
        return retrieveFunc.apply(matzipList, requestLocation, range);
    }

}

