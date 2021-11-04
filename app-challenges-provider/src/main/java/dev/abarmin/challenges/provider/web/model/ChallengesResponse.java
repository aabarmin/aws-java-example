package dev.abarmin.challenges.provider.web.model;

import dev.abarmin.challenges.provider.domain.Challenge;
import java.util.Collection;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChallengesResponse {
  private final Collection<Challenge> challenges;
}
