package com.example.geo.controller;

import com.example.geo.domain.Location;
import com.example.geo.domain.MatzipListRetrieveType;
import com.example.geo.dto.MatzipSummaryReponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
@DisplayName("맛집 목록 조회 API 통합 테스트")
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MatzipListRetrieveControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String url = "/api/matzips";
    // query params name
    private final String LATITUDE = "lat";
    private final String LONGITUDE = "lon";
    private final String RANGE = "range";
    private final String RETRIEVE_TYPE = "type";

    private final Location requestLocation = Location.of("37.44749167", "127.1477194");

    @Test
    @DisplayName("요청 위치 주변 0.01km 이내의 맛집 목록을 거리순으로 조회 했는데 없으면 비어있는 list를 반환한다")
    void retrieveMatzipList_distance_10m() throws Exception {
        // given
        String range = "0.01";
        String type = MatzipListRetrieveType.DISTANCE.name();

        // when, then
        mockMvc.perform(get(url)
                       .param(LATITUDE, requestLocation.getLat())
                       .param(LONGITUDE, requestLocation.getLon())
                       .param(RETRIEVE_TYPE, type)
                       .param(RANGE, range))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("요청 위치 주변 1.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_1000m() throws Exception {
        // given
        String range = "1.0";
        String type = MatzipListRetrieveType.DISTANCE.name();
        List<String> expected = Arrays.asList("맛집 2", "맛집 1");

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(responseBody);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }

    @Test
    @DisplayName("요청 위치 주변 5.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_5000m() throws Exception {
        // given
        String range = "5.0";
        String type = MatzipListRetrieveType.DISTANCE.name();
        List<String> expected = Arrays.asList("맛집 2", "맛집 1", "맛집 3", "맛집 5", "맛집 7", "맛집 6");

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }

    @Test
    @DisplayName("요청 위치 주변 10.0km 이내의 맛집 목록을 거리순으로 조회한다")
    void retrieveMatzipList_distance_10000m() throws Exception {
        // given
        String range = "10.0";
        String type = MatzipListRetrieveType.DISTANCE.name();
        List<String> expected = Arrays.asList(
                "맛집 2", "맛집 1", "맛집 3", "맛집 5", "맛집 7",
                "맛집 6", "맛집 4", "맛집 8", "맛집 11", "맛집 9"
        );

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }

    @Test
    @DisplayName("요청 위치 주변 0.01km 이내의 맛집 목록을 평점 순으로 조회 했는데 없으면 비어있는 list를 반환한다")
    void retrieveMatzipList_avgRating_10m() throws Exception {
        // given
        String range = "0.01";
        String type = MatzipListRetrieveType.AVG_RATING.name();

        // when, then
        mockMvc.perform(get(url)
                       .param(LATITUDE, requestLocation.getLat())
                       .param(LONGITUDE, requestLocation.getLon())
                       .param(RETRIEVE_TYPE, type)
                       .param(RANGE, range))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("요청 위치 주변 1.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_1000m() throws Exception {
        // given
        String range = "1.0";
        String type = MatzipListRetrieveType.AVG_RATING.name();
        List<String> expected = Arrays.asList("맛집 1", "맛집 2");

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(responseBody);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }

    @Test
    @DisplayName("요청 위치 주변 5.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_5000m() throws Exception {
        // given
        String range = "5.0";
        String type = MatzipListRetrieveType.AVG_RATING.name();
        List<String> expected = Arrays.asList("맛집 3", "맛집 7", "맛집 1", "맛집 2", "맛집 5", "맛집 6");

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }

    @Test
    @DisplayName("요청 위치 주변 10.0km 이내의 맛집 목록을 평점 순으로 조회한다")
    void retrieveMatzipList_avgRating_10000m() throws Exception {
        // given
        String range = "10.0";
        String type = MatzipListRetrieveType.AVG_RATING.name();
        List<String> expected = Arrays.asList(
                "맛집 9", "맛집 3", "맛집 11", "맛집 7", "맛집 1",
                "맛집 2", "맛집 4", "맛집 5", "맛집 6", "맛집 8"
        );

        // when, then
        String responseBody = mockMvc.perform(get(url)
                                             .param(LATITUDE, requestLocation.getLat())
                                             .param(LONGITUDE, requestLocation.getLon())
                                             .param(RETRIEVE_TYPE, type)
                                             .param(RANGE, range))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(jsonPath("$.length()").value(expected.size()))
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString(StandardCharsets.UTF_8);

        List<MatzipSummaryReponse> resMatzipList = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertThat(resMatzipList).extracting(MatzipSummaryReponse::getName)
                                 .containsExactlyElementsOf(expected);
    }
}

