package dev.abarmin.challenges.provider.web.model;

import java.util.Map;
import lombok.Data;

@Data
public class ChallengesRequest {
  private Map<String, Integer> request;
}
