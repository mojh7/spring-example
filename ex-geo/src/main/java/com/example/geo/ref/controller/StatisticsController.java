/*
package com.example.geo.controller;

import com.example.geo.dto.StatisticsGetRequest;
import com.example.geo.dto.StatisticsGetResponse;
import com.example.geo.service.StatisticsService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/api/statistics")
    public ResponseEntity<List<StatisticsGetResponse>> getStatistics(@ModelAttribute @Valid StatisticsGetRequest statisticsGetRequest,
                                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String memberAccountName = userDetails.getMember().getAccountName();
        List<StatisticsGetResponse> statisticsGetResponses = statisticsService.getStatistics(statisticsGetRequest, memberAccountName);
        return ResponseEntity.ok(statisticsGetResponses);
    }
}
*/
