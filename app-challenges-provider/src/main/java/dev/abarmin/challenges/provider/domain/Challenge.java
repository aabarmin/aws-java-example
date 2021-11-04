package dev.abarmin.challenges.provider.domain;

import java.util.Collection;
import lombok.Data;

@Data
public class Challenge {
  private String question;
  private Collection<ChallengeAnswer> answers;
}
