package com.inventory.manager.service.scheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by surender.s on 11/12/17.
 */
@Slf4j
@DisallowConcurrentExecution
@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class SampleJob extends QuartzJobBean {

    public static final String JOB = "SAMPLE_JOB";
    public static final String JOB_GROUP = "SAMPLE_JOB_GROUP";
    public static final String TRIGGER = "SAMPLE_TRIGGER";
    public static final String TRIGGER_GROUP = "SAMPLE_TRIGGER_GROUP";
    public static final String INVENTORY_ID = "INVENTORY_ID";


    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            log.info("Executing sample job");
        } catch (Exception exception) {
            log.error("Exception occurred while executing Job:" , exception);
        }
    }
}
