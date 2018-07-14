package com.im.jobs.controllers;

import com.im.jobs.BaseJobsTest;
import com.im.jobs.services.WorkersService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkerJobsController.class)
public class WorkerJobsControllerTest extends BaseJobsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkersService service;

    @Test
    public void testRestGetJobsForWorker() throws Exception {

        given(service.getJobsForWorkerId(12))
                .willReturn(jobs);

        MvcResult result = mockMvc.perform(get("/jobs?userid=12"))
                .andExpect(status().isOk()).andReturn();

        verify(service, times(1))
                .getJobsForWorkerId(12);

        Assert.assertEquals("Check responce string.", result.getResponse().getContentAsString(),
                "[{\"driverLicenseRequired\":true,\"requiredCertificates\":" +
                        "[\"Healthy Living Promoter\",\"Calm in the Eye of the Storm\"," +
                        "\"The Encouraging Word Award\"],\"location\":{\"longitude\":" +
                        "\"13.864602\",\"latitude\":\"49.93359\"},\"billRate\":" +
                        "\"$10.11\",\"workersRequired\":5,\"startDate\":" +
                        "\"2015-07-29T19:30:40.000Z\",\"about\":\"About Text\",\"jobTitle\":" +
                        "\"Plumber\",\"company\":\"Job Test Company\",\"guid\":" +
                        "\"123456ty89\",\"jobId\":1}]");
    }
}
