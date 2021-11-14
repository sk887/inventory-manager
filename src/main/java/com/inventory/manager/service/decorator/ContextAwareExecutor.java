package com.inventory.manager.service.decorator;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;

public class ContextAwareExecutor implements TaskExecutor {

    private final Executor executor;

    public ContextAwareExecutor(Executor executor) {
        this.executor = executor;
    }


    @Override
    public void execute(Runnable command) {
        Runnable runnable = wrap(command);
        executor.execute(runnable);
    }

    private Runnable wrap(Runnable command) {

        // get a copy of the values of calling Thread's MDC
        final Map<String, String> callerContextCopy = MDC.getCopyOfContextMap();

        return () -> {
            // get a copy of the values of executing-Thread's MDC
            final Map<String, String> executorContextCopy = MDC.getCopyOfContextMap();

            MDC.clear();
            if (callerContextCopy != null) {
                // set the desired context that was present at point of calling execute
                MDC.setContextMap(callerContextCopy);
            }

            // execute the command
            command.run();

            MDC.clear();
            if (executorContextCopy != null) {
                // reset the context
                MDC.setContextMap(executorContextCopy);
            }
        };
    }
}
