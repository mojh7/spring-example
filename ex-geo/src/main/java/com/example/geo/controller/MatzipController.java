package com.example.geo.controller;

import com.example.geo.dto.MatzipSummaryReponse;
import com.example.geo.dto.MatzipListRetrieveRequest;
import com.example.geo.service.MatzipRetrieveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatzipController {
    private final MatzipRetrieveService matzipRetrieveService;

    @GetMapping("/api/matzips")
    public ResponseEntity<List<MatzipSummaryReponse>> retrieveMatzipList(@ModelAttribute @Valid MatzipListRetrieveRequest request) {
        List<MatzipSummaryReponse> response = matzipRetrieveService.retrieveMatzipList(request);
        return ResponseEntity.ok(response);
    }
}
