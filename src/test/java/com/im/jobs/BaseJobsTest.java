package com.im.jobs;

import com.im.jobs.domain.jobs.Job;
import com.im.jobs.domain.jobs.Location;
import com.im.jobs.domain.workers.JobSearchAddress;
import com.im.jobs.domain.workers.Worker;
import org.junit.Before;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BaseJobsTest {

    protected List<Job> jobs;
    protected Worker worker;

    @Before
    public void setup() {
        jobs = new ArrayList<>();
        Job job = new Job();
        job.setAbout("About Text");
        job.setBillRate("$10.11");
        job.setCompany("Job Test Company");
        job.setDriverLicenseRequired(true);
        job.setJobTitle("Plumber");
        job.setJobId(1);
        job.setGuid("123456ty89");

        List<String> serts = new ArrayList<>();
        serts.add("Healthy Living Promoter");
        serts.add("Calm in the Eye of the Storm");
        serts.add("The Encouraging Word Award");
        job.setRequiredCertificates(serts);

        job.setWorkersRequired(5);
        job.setStartDate(LocalDateTime.of(2015, Month.JULY, 29,
                19, 30, 40));
        Location loc = new Location();
        loc.setLatitude("49.93359");
        loc.setLongitude("13.864602");

        job.setLocation(loc);

        jobs.add(job);

        worker = new Worker();
        worker.setActive(true);
        worker.setUserId(12);
        worker.setHasDriversLicense(true);
        worker.setTransportation("CAR");
        worker.setGuid("123456ty789");

        List<String> workerSerts = new ArrayList<>();
        workerSerts.add("Healthy Living Promoter");
        workerSerts.add("Calm in the Eye of the Storm");
        workerSerts.add("The Encouraging Word Award");
        worker.setCertificates(  workerSerts);

        List<String> skills = new ArrayList<>();
        skills.add("Plumber");
        skills.add("Electrician");
        skills.add("Crayon Evangelist");
        skills.add("Ambassador of buzz");
        worker.setSkills(skills);

        JobSearchAddress jsa = new JobSearchAddress();
        jsa.setUnit("km");
        jsa.setMaxJobDistance(50);
        jsa.setLatitude("50.231845");
        jsa.setLongitude("13.870996");
        worker.setJobSearchAddress(jsa);
    }
}
