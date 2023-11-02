package com.example.geo.service;

import com.example.geo.domain.Location;
import com.example.geo.domain.MatzipListRetrieveType;
import com.example.geo.dto.MatzipListRetrieveRequest;
import com.example.geo.dto.MatzipSummaryReponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * <p>
 * 맛집 목록 조회 service layer 테스트
 * </p>
 * <p>
 * 따로 불러와야되는 더미 데이터(matzip.sql)가 있어서
 * 본래 팀에서 정한 클래스 이름 규칙(MatzipServiceTest)과 다르게 생성함
 * </p>
 * 맛집과의 거리 distance와 몇 km 이내의 맛집 조회 조건 range는 "1.0" 와 같은 km 단위이나
 * 함수명으로 .을 표기할 수 없고 1_0 와 같은 형태도 애매하다고 생각해 meter로 표기함
 */
@Sql("/db/matzip.sql")
@DisplayName("맛집 목록 조회 Service 통합 테스트")
@Transactional
@SpringBootTest
class MatzipListRetrieveServiceTest {

    @Autowired
    private MatzipRetrieveService matzipRetrieveService;

    // 시군구 데이터 성남시
    private final Location requestLocation = Location.of("37.44749167", "127.1477194");

    @Test
    @DisplayName("요청 위치 주변 0.01km 이내의 맛집 목록을 거리순으로 조회 했는데 없으면 비어있는 list를 반환한다")
    void retrieveMatzipList_distance_10m() {
        // given
        String range = "0.01";
        MatzipListRetrieveType type = MatzipListRetrieveType.DISTANCE;
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertThat(result.size()).isEqualTo(0);
    }


    @Test
    @DisplayName("요청 위치 주변 1.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_1000m() {
        // given
        String range = "1.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.DISTANCE;
        List<String> expected = Arrays.asList("맛집 2", "맛집 1");
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(2),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }

    @Test
    @DisplayName("요청 위치 주변 5.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_5000m() {
        // given
        String range = "5.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.DISTANCE;
        List<String> expected = Arrays.asList("맛집 2", "맛집 1", "맛집 3", "맛집 5", "맛집 7", "맛집 6");
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(expected.size()),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }

    @Test
    @DisplayName("요청 위치 주변 10.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_10000m() {
        // given
        String range = "10.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.DISTANCE;
        List<String> expected = Arrays.asList(
                "맛집 2", "맛집 1", "맛집 3", "맛집 5", "맛집 7",
                "맛집 6", "맛집 4", "맛집 8", "맛집 11", "맛집 9"
        );
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(expected.size()),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }

    @Test
    @DisplayName("요청 위치 주변 0.01km 이내의 맛집 목록을 평점 순으로 조회 했는데 없으면 비어있는 list를 반환한다")
    void retrieveMatzipList_avgRating_10m() {
        // given
        String range = "0.01";
        MatzipListRetrieveType type = MatzipListRetrieveType.AVG_RATING;
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertThat(result.size()).isEqualTo(0);
    }


    @Test
    @DisplayName("요청 위치 주변 1.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_1000m() {
        // given
        String range = "1.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.AVG_RATING;
        List<String> expected = Arrays.asList("맛집 1", "맛집 2");
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(2),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }

    @Test
    @DisplayName("요청 위치 주변 5.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_5000m() {
        // given
        String range = "5.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.AVG_RATING;
        List<String> expected = Arrays.asList("맛집 3", "맛집 7", "맛집 1", "맛집 2", "맛집 5", "맛집 6");
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(expected.size()),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }

    @Test
    @DisplayName("요청 위치 주변 10.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_10000m() {
        // given
        String range = "10.0";
        MatzipListRetrieveType type = MatzipListRetrieveType.AVG_RATING;
        List<String> expected = Arrays.asList(
                "맛집 9", "맛집 3", "맛집 11", "맛집 7", "맛집 1",
                "맛집 2", "맛집 4", "맛집 5", "맛집 6", "맛집 8"
        );
        MatzipListRetrieveRequest request = MatzipListRetrieveRequest.builder()
                                                                     .lat(requestLocation.getLat())
                                                                     .lon(requestLocation.getLon())
                                                                     .range(range)
                                                                     .type(type)
                                                                     .build();

        // when
        List<MatzipSummaryReponse> result = matzipRetrieveService.retrieveMatzipList(request);

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(expected.size()),
                () -> assertThat(result).extracting(MatzipSummaryReponse::getName)
                                        .containsExactlyElementsOf(expected)
        );
    }
}