package dev.abarmin.challenges.provider.domain;

import lombok.Data;

@Data
public class ChallengeAnswer {
  private String answer;
  private boolean correct;
}
