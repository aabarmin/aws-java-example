package dev.abarmin.challenges.provider.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProviderRequest {
  private final String discipline;
  private final int number;
}
