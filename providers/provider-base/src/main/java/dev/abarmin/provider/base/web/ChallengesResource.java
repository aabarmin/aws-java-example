package dev.abarmin.provider.base.web;

import dev.abarmin.provider.base.domain.Challenge;
import dev.abarmin.provider.base.provider.ChallengesProvider;
import dev.abarmin.provider.base.web.model.ChallengesRequest;
import dev.abarmin.provider.base.web.model.ChallengesResponse;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengesResource {
  @Autowired
  private ChallengesProvider provider;

  @PostMapping("/provide")
  public ChallengesResponse provide(@RequestBody ChallengesRequest request) {
    final Collection<Challenge> provided = provider.provide(request.getNumber());
    return new ChallengesResponse(
        request.getDiscipline(),
        provided
    );
  }
}
