package com.example.geo.controller;

import com.example.geo.service.MatzipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatzipController {
    private final MatzipService matzipService;

//    @GetMapping("/api/statistics")
//    public ResponseEntity<List<StatisticsGetResponse>> getStatistics(@ModelAttribute @Valid StatisticsGetRequest statisticsGetRequest,
//                                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String memberAccountName = userDetails.getMember().getAccountName();
//        List<StatisticsGetResponse> statisticsGetResponses = statisticsService.getStatistics(statisticsGetRequest, memberAccountName);
//        return ResponseEntity.ok(statisticsGetResponses);
//    }
}
