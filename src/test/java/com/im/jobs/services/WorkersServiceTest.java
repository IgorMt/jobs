package com.im.jobs.services;

import com.im.jobs.BaseJobsTest;
import com.im.jobs.domain.jobs.Job;
import com.im.jobs.domain.workers.Worker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkersServiceTest extends BaseJobsTest {

    @Autowired
    WorkersService workersService;

    @Test
    public void testIsJobFitToWorker() throws Exception {
        assertTrue("This Job is fit to worker.", ReflectionTestUtils.invokeMethod
                (workersService,
                        "isJobFitToWorker",
                        jobs.get(0), worker));
    }

    @Test
    public void testIsJobNotFitToWorkerWithSkills() throws Exception {
        worker.getSkills().remove("Plumber");
        assertFalse("This Job is not fit to worker with skills.",
                ReflectionTestUtils.invokeMethod(workersService,
                        "isJobFitToWorker",
                        jobs.get(0), worker));
        worker.getSkills().add("Plumber");
    }

    @Test
    public void testIsJobNotFitToWorkerWithDriveLicence() throws Exception {
        worker.setHasDriversLicense(false);
        assertFalse("This Job is not fit to worker with Drive License.",
                ReflectionTestUtils.invokeMethod(workersService,
                        "isJobFitToWorker",
                        jobs.get(0), worker));
        worker.setHasDriversLicense(true);
    }

    @Test
    public void testIsFitCertificates() throws Exception {
        assertTrue("Worker certificates are fit to Job", ReflectionTestUtils.invokeMethod
                (workersService,
                        "isFitWithCertificates",
                        jobs.get(0), worker));
    }

    @Test
    public void testIsNotFitCertificates() throws Exception {
        worker.getCertificates().remove("The Encouraging Word Award");
        assertFalse("Worker certificates are not fit to Job", ReflectionTestUtils.invokeMethod
                (workersService,
                        "isFitWithCertificates",
                        jobs.get(0), worker));
        worker.getCertificates().add("The Encouraging Word Award");
    }


}
