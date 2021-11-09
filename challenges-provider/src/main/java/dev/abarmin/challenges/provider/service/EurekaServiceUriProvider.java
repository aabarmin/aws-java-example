package dev.abarmin.challenges.provider.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "provider", name = "type", havingValue = "eureka", matchIfMissing = true)
public class EurekaServiceUriProvider implements ServiceUriProvider {
  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private InstanceSelector selector;

  @Override
  public String provide(String service) {
    // getting a service which provides a given science
    final List<ServiceInstance> instances = discoveryClient.getInstances(service);
    log.info("{} instances found", instances.size());

    final ServiceInstance instance = selector.select(instances);
    return buildUrl(instance);
  }

  private String buildUrl(final ServiceInstance instance) {
    return instance.getUri().toString() + "/provide";
  }
}
