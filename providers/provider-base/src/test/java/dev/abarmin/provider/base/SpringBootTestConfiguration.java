package dev.abarmin.provider.base;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(ProviderConfiguration.class)
public class SpringBootTestConfiguration {
}
