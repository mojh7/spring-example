package com.example.geo.temp;

import com.example.geo.dto.MatzipSummaryReponse;
import com.example.geo.repository.MatzipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql("/db/matzip.sql")
@SpringBootTest
public class GeoTest {

    @Autowired
    MatzipRepository matzipRepository;

    /*@Test
    void test1() {
        Location lo1 = Location.of("37.44749167", "127.1477194");
        Location lo2 = Location.of("37.205", "127.1385");
        // ", "

        System.out.println(lo1.distanceFrom(lo2));
        System.out.println(lo2.distanceFrom(lo1));
    }*/



    @Test
    void test2() {

        matzipRepository.findAll().stream()
                .map(m -> MatzipSummaryReponse.of(m, 0.0f))
                .forEach(m -> System.out.println(m.getName() + ", " + m.getAvgRating()));
    }
}
