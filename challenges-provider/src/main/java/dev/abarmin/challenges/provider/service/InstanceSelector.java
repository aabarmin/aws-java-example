package dev.abarmin.challenges.provider.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.Collection;
import java.util.List;

public interface InstanceSelector {
  ServiceInstance select(List<ServiceInstance> instances);
}
