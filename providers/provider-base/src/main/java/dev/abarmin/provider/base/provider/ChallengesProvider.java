package dev.abarmin.provider.base.provider;

import dev.abarmin.provider.base.domain.Challenge;
import java.util.Collection;

public interface ChallengesProvider {
  /**
   * Provide a given number of challenges.
   *
   * @param number of challenges providing.
   * @return a collection of challenges.
   */
  Collection<Challenge> provide(int number);
}
