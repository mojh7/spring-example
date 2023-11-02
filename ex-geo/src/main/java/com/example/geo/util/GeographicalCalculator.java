package com.example.geo.util;

public final class GeographicalCalculator {

    public static double latLonToKm(double[] point1, double[] point2) {
        double lat1 = point1[1];
        double lon1 = point1[0];
        double lat2 = point2[1];
        double lon2 = point2[0];

        double R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public static double latLonToKm(String point1, String point2) {
        // TODO: 위도 경도 string -> double[2] 형태로 변환
        return latLonToKm(new double[]{ 0, 0 }, new double[]{ 0, 0 });
    }
}
