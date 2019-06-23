package com.inventory.manager.service.scheduler.service;

/**
 * Created by surender.s on 11/12/17.
 */
public interface SchedulerService<T>{

    void clearAllJob() throws Exception;

    void registerNewJob(T entity, int frequencyTimeInMinutes) throws Exception;

    void deleteJob(T entity) throws Exception;
}
