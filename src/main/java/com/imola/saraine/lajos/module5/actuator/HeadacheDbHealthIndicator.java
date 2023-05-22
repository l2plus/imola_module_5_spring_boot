package com.imola.saraine.lajos.module5.actuator;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class HeadacheDbHealthIndicator implements HealthIndicator {

    @Autowired
    private HeadacheService headacheService;

    @Override
    public Health health() {
        Collection<Headache> headaches = headacheService.allHeadaches();
        if (Objects.isNull(headaches) | headaches.size() == 0) {
            return Health.down().withDetail("headache count", 0).build();
        }
        return Health.up().withDetail("headache count is ", headaches.size()).build();
    }
}
