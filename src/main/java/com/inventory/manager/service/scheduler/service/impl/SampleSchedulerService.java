package com.inventory.manager.service.scheduler.service.impl;


import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.scheduler.job.SampleJob;
import com.inventory.manager.service.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by surender.s on 11/12/17.
 */
@Service
@Slf4j
public class SampleSchedulerService implements SchedulerService<Inventory> {

    @Inject
    SchedulerFactoryBean schedulerFactory;

    @Override
    public void clearAllJob() throws Exception {
        try {
            schedulerFactory.getScheduler().clear();
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @Override
    public void registerNewJob(Inventory inventory, int frequencyIntervalInMinutes) throws Exception {
        try {
            schedulerFactory.getScheduler().scheduleJob(getJobDetail(inventory), Collections.singleton(getTrigger(inventory,
                    frequencyIntervalInMinutes)), true);
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @Override
    public void deleteJob(Inventory inventory
    ) throws Exception {
        try {
            schedulerFactory.getScheduler().deleteJob(JobKey.jobKey(getName(inventory
                    , SampleJob.JOB),
                    SampleJob.TRIGGER_GROUP));
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    private JobDetail getJobDetail(Inventory inventory) throws Exception {
        return newJob(SampleJob.class)
                .withIdentity(getName(inventory, SampleJob.JOB), SampleJob.JOB_GROUP)
                .usingJobData(SampleJob.INVENTORY_ID, String.valueOf(inventory.getId()))
                .storeDurably().build();
    }

    private String getName(Inventory inventory, String prefix) {
        return String.format("%s_%s", prefix, String.valueOf(inventory.getId()));
    }

    private Trigger getTrigger(Inventory inventory, int frequencyIntervalInMinutes) {
        return TriggerBuilder.newTrigger()
                .withIdentity(getName(inventory, SampleJob.TRIGGER), SampleJob.JOB_GROUP)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(frequencyIntervalInMinutes)
                        .repeatForever())
                .build();
    }
}
