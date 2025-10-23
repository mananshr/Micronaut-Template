package io.github.mananshr.crud;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import io.github.mananshr.crud.util.Logger;

@OpenAPIDefinition(
    info = @Info(
            title = "mcronaut",
            version = "0.0"
    )
)
public class Application {    

    @ContextConfigurer
    public static class Configurer implements ApplicationContextConfigurer {
        @Override
        public void configure(@NonNull ApplicationContextBuilder builder) {
            // Ensure dev environment loaded by default during local runs
            builder.defaultEnvironments("dev");
        }
    }
    public static void main(String[] args) {
        // use instance logger
        Logger.LoggerInstance log = Logger.getLogger(Application.class);
        log.info("Starting application with args: %s", java.util.Arrays.toString(args));
        try {
            Micronaut.run(Application.class, args);
            log.success("Application started");
        } catch (Throwable t) {
            // Log startup error and rethrow to allow process to fail visibly
            log.error("Application failed to start: %s", t.getMessage());
            throw t;
        }
    }
}