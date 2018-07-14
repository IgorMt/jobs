package com.im.jobs.controllers;

import com.im.jobs.domain.jobs.Job;
import com.im.jobs.exceptions.JobsBusinessException;
import com.im.jobs.services.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author imaltsev
 * @since 14/07/18
 * <p>
 * The RestController returning Collection of Jobs
 */
@RestController
public class WorkerJobsController {

    @Autowired
    WorkersService workersService;

    /**
     * Rest server method: gets request and returns suitable jobs for Worker.
     *
     * @param userid  - worker userId
     * @return List<Job> - the list of jobs
     */
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public List<Job> getJobsByWorkerId(
            @RequestParam(value = "userid") Integer userid)
            throws IOException, JobsBusinessException {
        return workersService.getJobsForWorkerId(userid);
    }
}
