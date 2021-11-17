package dev.abarmin.provider.base.health;

import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.PostConstruct;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
  @Override
  public Health health() {
    final int nextValue = ThreadLocalRandom.current().nextInt(2);
    if (nextValue == 0) {
      return Health.down()
          .status("Invalid phase of the Moon")
          .build();
    }
    return Health.up().build();
  }
}
