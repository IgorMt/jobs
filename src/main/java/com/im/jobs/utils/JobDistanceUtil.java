package com.im.jobs.utils;

public class JobDistanceUtil {

    /**
     * This method returns - as true or false - distance to Job for Worker.
     * Method applies maxDistance parameter in kilometers only.
     *
     * @param lat1 - worker latitude
     * @param lng1 - worker longitude
     * @param lat2 - job latitude
     * @param lng2 - job longitude
     * @param maxDistance - max distance in km
     * @return boolean - is fit or not
     */
    public static boolean isInJobDistanceKm(float lat1, float lng1, float lat2, float lng2, float
            maxDistance) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        if (dist / 1000 > maxDistance) {
            return false;
        }
        return true;
    }
}
