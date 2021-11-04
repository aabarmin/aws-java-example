package dev.abarmin.provider.base.domain;

import lombok.Data;

@Data
public class ChallengeAnswer {
  private String answer;
  private boolean correct;
}
