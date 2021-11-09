package dev.abarmin.challenges.provider.service;

import com.netflix.discovery.converters.Auto;
import dev.abarmin.challenges.provider.domain.Challenge;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChallengeProvider {
  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private InstanceSelector selector;

  public Collection<Challenge> provide(final Map<String, Integer> request) {
    final Collection<Challenge> challenges = new ArrayList<>();

    for (Map.Entry<String, Integer> entry : request.entrySet()) {
      // getting a service which provides a given science
      final List<ServiceInstance> instances = discoveryClient.getInstances(getServiceName(entry.getKey()));
      log.info("{} instances found", instances.size());

      final ServiceInstance instance = selector.select(instances);

      // building an url to request challenges
      final String url = buildUrl(instance);
      log.info("Sending request to {}", url);

      // requesting data
      final ProviderRequest providerRequest = new ProviderRequest(entry.getKey(), entry.getValue());
      final ProviderResponse response = restTemplate.postForObject(url, providerRequest, ProviderResponse.class);

      // done
      challenges.addAll(response.getChallenges());
    }

    return challenges;
  }

  private String getServiceName(final String discipline) {
    return "provider-" + discipline;
  }

  private String buildUrl(final ServiceInstance instance) {
    return instance.getUri().toString() + "/provide";
  }
}
