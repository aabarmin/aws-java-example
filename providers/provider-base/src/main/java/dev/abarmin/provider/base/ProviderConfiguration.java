package dev.abarmin.provider.base;

import dev.abarmin.provider.base.provider.ChallengesProvider;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:actuator.properties")
public class ProviderConfiguration {
  @Bean
  public MeterBinder challengesCount(ChallengesProvider provider) {
    return registry ->
        Gauge.builder("challenges.count", provider::count)
            .baseUnit("ITEM")
            .description("Amount of challenges registered in the app")
            .register(registry);
  }
}
