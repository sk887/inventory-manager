package com.inventory.manager.service.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
@Component
public class RotationTaskHealthIndicator implements HealthIndicator {

    private final Path path;

    @Autowired
    public RotationTaskHealthIndicator(@Value("${health.rotation.config.file.path}") String path) {
        log.info("Rotatio file path " + path);

        this.path = Paths.get(path);
    }

    @Override
    public Health health() {
        if (Files.exists(path)) {
            return Health.up().withDetail("Health", "Machine is in Rotations").build();
        } else {
            return Health.down().withDetail("Health", "Machine is out of Rotation").build();
        }
    }
}
