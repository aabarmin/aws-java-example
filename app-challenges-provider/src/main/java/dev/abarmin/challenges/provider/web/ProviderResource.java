package dev.abarmin.challenges.provider.web;

import dev.abarmin.challenges.provider.domain.Challenge;
import dev.abarmin.challenges.provider.service.ChallengeProvider;
import dev.abarmin.challenges.provider.web.model.ChallengesRequest;
import dev.abarmin.challenges.provider.web.model.ChallengesResponse;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderResource {
  @Autowired
  private ChallengeProvider provider;

  @PostMapping("/provide")
  public ChallengesResponse provide(@RequestBody ChallengesRequest request) {
    final Collection<Challenge> challenges = provider.provide(request.getRequest());
    return new ChallengesResponse(challenges);
  }
}
