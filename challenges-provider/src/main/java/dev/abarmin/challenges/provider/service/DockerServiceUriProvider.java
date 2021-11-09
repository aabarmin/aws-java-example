package dev.abarmin.challenges.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "provider", name = "type", havingValue = "docker")
public class DockerServiceUriProvider implements ServiceUriProvider {
  @Override
  public String provide(String service) {
    return String.format(
        "http://%s:8080/provide",
        service
    );
  }
}
