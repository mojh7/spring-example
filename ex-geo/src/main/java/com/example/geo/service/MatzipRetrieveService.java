package com.example.geo.service;

import com.example.geo.domain.Location;
import com.example.geo.dto.MatzipListRetrieveRequest;
import com.example.geo.dto.MatzipSummaryReponse;
import com.example.geo.repository.MatzipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatzipRetrieveService {
    private final MatzipRepository matzipRepository;

    public List<MatzipSummaryReponse> retrieveMatzipList(MatzipListRetrieveRequest request) {
        Location requestLocation = Location.of(request.getLat(), request.getLon());
        double range = Double.parseDouble(request.getRange());
        return request.getType()
                      .retrieve(matzipRepository.findAll(), requestLocation, range);
    }
}
