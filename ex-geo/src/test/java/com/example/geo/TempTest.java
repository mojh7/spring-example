/*
package com.example.geo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TempTest {

    @Test
    void Test() {
        long maxCnt = 10000;
        long min = 1;
        long max = 5;

        float avgScore1 = 0;
        double avgScore2 = 0;
        long totalScore = 0;

        long cnt = 0;
//        float cntF = 0;
//        double cntD = 0;
//        long cntL = 0;

        // 평점 = 총점 / 갯수

        // 평점, 갯수

        for(int idx = 0; idx < maxCnt; idx++) {
            long currScore = (long) (Math.random() * 5 + 1);

            // 평점(float)과 별점 등록 인원 수 관리
            avgScore1 = (avgScore1 * cnt + currScore) / (cnt + 1);

            // 평점(double)과 별점 등록 인원 수 관리
            avgScore2 = (avgScore2 * cnt + currScore) / (cnt + 1);

            // 총점과 별점 등록 인원 수 관리
            totalScore += currScore;

            cnt++;
//            cntF += 1.0f;
//            cntD += 1.0d;
//            cntL += 1L;
        }

        float avgFromTotalScore = totalScore / cnt;
        double avgFromTotalScore2 = totalScore / cnt;

        System.out.println("float 평점 : " + avgScore1);
        System.out.println("double 평점 : " + avgScore2);
        System.out.println("float 총점 / cnt : " + avgFromTotalScore);
        System.out.println("double 총점 / cnt : " + avgFromTotalScore2);
        System.out.println("cnt : " + cnt);
        //System.out.println("float 별점 cnt : " + cntF);
        //System.out.println("double 별점 cnt : " + cntD);
        //System.out.println("long 별점 cnt : " + cntL);

        Assertions.assertThat(avgScore1).isEqualTo(avgFromTotalScore);
        Assertions.assertThat(avgScore2).isEqualTo(avgFromTotalScore2);
    }
}
*/
