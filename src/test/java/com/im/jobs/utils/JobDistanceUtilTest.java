package com.im.jobs.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobDistanceUtilTest {

    @Test
    public void testJobDistanceIn() {
        assertTrue("Job location fit to worker ",
                JobDistanceUtil.isInJobDistanceKm(
                        Float.valueOf("50.231845"),
                        Float.valueOf("13.870996"),
                        Float.valueOf("50.091271"),
                        Float.valueOf("13.810606"),
                        Float.valueOf("50")));
    }

    @Test
    public void testJobDistanceOut() {
        assertFalse("Job location not fit to worker ",
                JobDistanceUtil.isInJobDistanceKm(
                        Float.valueOf("50.231845"),
                        Float.valueOf("13.870996"),
                        Float.valueOf("50.091271"),
                        Float.valueOf("13.810606"),
                        Float.valueOf("10")));
    }
}
