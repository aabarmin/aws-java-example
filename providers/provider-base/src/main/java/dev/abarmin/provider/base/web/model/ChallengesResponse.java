package dev.abarmin.provider.base.web.model;

import dev.abarmin.provider.base.domain.Challenge;
import java.util.Collection;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChallengesResponse {
  private final String discipline;
  private final Collection<Challenge> challenges;
}
