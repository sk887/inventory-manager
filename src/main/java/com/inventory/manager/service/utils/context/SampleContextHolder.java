package com.inventory.manager.service.utils.context;

import lombok.ToString;

public class SampleContextHolder {
    //Use InheritableThreadLocal if using any child thread.
    private static final ThreadLocal<Context> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();


    public static void setContext(Context context) {
        CONTEXT_THREAD_LOCAL.set(context);
    }


    public static Context get() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    public static void clear() {
        CONTEXT_THREAD_LOCAL.remove();
    }

    @ToString
    public static class Context {
        private String name;

        public Context(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
