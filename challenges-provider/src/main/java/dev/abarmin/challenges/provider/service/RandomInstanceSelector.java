package dev.abarmin.challenges.provider.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
public class RandomInstanceSelector implements InstanceSelector {
  private final Random random = new Random();

  @Override
  public ServiceInstance select(List<ServiceInstance> instances) {
    return instances.get(random.nextInt(instances.size()));
  }
}
