package com.sowmik.springboot_api.healthchecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return null;
    }

    @Override
    public Health health() {
        boolean error = false;

        if(error){
            return Health.down().withDetail("Error key", 1234).build(); //here in second parameter you can return an object or an exception etc...
        }
        return Health.up().build();
    }
}
