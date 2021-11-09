package dev.abarmin.challenges.provider.service;

import dev.abarmin.challenges.provider.domain.Challenge;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ChallengeProvider {
  @Autowired
  private ServiceUriProvider provider;

  @Autowired
  private RestTemplate restTemplate;

  public Collection<Challenge> provide(final Map<String, Integer> request) {
    final Collection<Challenge> challenges = new ArrayList<>();

    for (Map.Entry<String, Integer> entry : request.entrySet()) {
      final String url = provider.provide(getServiceName(entry.getKey()));
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
}
