package dev.abarmin.challenges.provider.service;

import dev.abarmin.challenges.provider.domain.Challenge;
import java.util.Collection;
import lombok.Data;

@Data
public class ProviderResponse {
  private String discipline;
  private Collection<Challenge> challenges;
}
