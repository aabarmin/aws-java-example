package dev.abarmin.provider.base.domain;

import java.util.Collection;
import lombok.Data;

@Data
public class Challenge {
  private String question;
  private Collection<ChallengeAnswer> answers;
}
