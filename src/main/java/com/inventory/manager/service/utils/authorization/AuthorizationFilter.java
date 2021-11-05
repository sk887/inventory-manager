package com.inventory.manager.service.utils.authorization;

import com.inventory.manager.service.utils.context.SampleContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import java.util.Objects;

/**
 * This class works fine with jersey but not getting invoked in spring boot project.
 */
@Priority(Priorities.AUTHENTICATION)
@Slf4j
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        Authorization authorization = null;

        if (resourceInfo.getResourceClass().isAnnotationPresent(Authorization.class)) {
            authorization = resourceInfo.getResourceMethod().getAnnotation(Authorization.class);
        }

        if (resourceInfo.getResourceMethod().isAnnotationPresent(Authorization.class)) {
            authorization = resourceInfo.getResourceMethod().getAnnotation(Authorization.class);
        }

        if (Objects.isNull(authorization)) {
            return;
        }

        SampleContextHolder.Context context = SampleContextHolder.get();

        log.info("context: " + context.toString() + "authorization: " + authorization.value());

    }
}
