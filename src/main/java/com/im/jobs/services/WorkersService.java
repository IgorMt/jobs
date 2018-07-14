package com.im.jobs.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.jobs.domain.jobs.Job;
import com.im.jobs.domain.workers.Worker;
import com.im.jobs.exceptions.JobsBusinessException;
import com.im.jobs.utils.JobDistanceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author imaltsev
 * @since 14/07/18
 * <p>
 * This is the Service class holds methods to return the suitable Jobs for Worker
 **/
@Service
public class WorkersService {

    @Value("${jobs.url}")
    private String jobsUrl;

    @Value("${workers.url}")
    private String workersUrl;

    @Value("${jobs.return.size}")
    private Integer returnSize;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * This method returns the list of suitable Jobs for Worker
     *
     * @param id - the Worker userId
     * @return List<Job> - the list of fit Jobs for selected worker
     */
    public List<Job> getJobsForWorkerId(int id) throws JobsBusinessException {
        try {
            Worker worker = getWorkerById(id).orElseThrow(() ->
                    new JobsBusinessException("Can't find the Worker for userId = " + id));

            return getJobs().stream().filter(p -> isJobFitToWorker(p, worker))
                    .limit(returnSize).collect(Collectors.toList());
        } catch (IOException e) {
            throw new JobsBusinessException("Can't read Workers or Jobs values.", e);
        }
    }

    private List<Job> getJobs() throws IOException {
        return mapper.readValue(new URL(jobsUrl), new TypeReference<List<Job>>() {
        });
    }

    private Optional<Worker> getWorkerById(int id) throws IOException {
        List<Worker> workers = mapper.readValue(new URL(workersUrl),
                new TypeReference<List<Worker>>() {
                });
        Optional<Worker> work = workers.stream().filter(p -> Integer.valueOf(p.getUserId())
                .equals(id)).findFirst();
        return work;
    }

    private boolean isJobFitToWorker(Job job, Worker worker) {
        //check Drive License
        if (job.getDriverLicenseRequired() == true && worker.getHasDriversLicense() == false) {
            return false;
        }
        //check worker skills to Job title
        if (CollectionUtils.isEmpty(worker.getSkills())
                || !worker.getSkills().contains(job.getJobTitle())) {
            return false;
        }
        //check worker certificates to required Job certificates
        if (!isFitWithCertificates(job, worker)) {
            return false;
        }
        //check job in distance for worker
        return JobDistanceUtil.isInJobDistanceKm(
                Float.valueOf(worker.getJobSearchAddress().getLatitude()),
                Float.valueOf(worker.getJobSearchAddress().getLongitude()),
                Float.valueOf(job.getLocation().getLatitude()),
                Float.valueOf(job.getLocation().getLongitude()),
                Float.valueOf(worker.getJobSearchAddress().getMaxJobDistance()));
    }

    private boolean isFitWithCertificates(Job job, Worker worker) {
        List<String> reqSert = !CollectionUtils.isEmpty(job.getRequiredCertificates())
                ? job.getRequiredCertificates() :
                new ArrayList<String>();
        reqSert.removeIf(Objects::isNull);

        List<String> workerSert = !CollectionUtils.isEmpty(worker.getCertificates())
                ? worker.getCertificates() :
                new ArrayList<String>();
        workerSert.removeIf(Objects::isNull);

        if (!CollectionUtils.isEmpty(reqSert)
                && CollectionUtils.isEmpty(workerSert)) {
            return false;
        }

        if (!workerSert.containsAll(reqSert)) {
            return false;
        }
        return true;
    }
}
