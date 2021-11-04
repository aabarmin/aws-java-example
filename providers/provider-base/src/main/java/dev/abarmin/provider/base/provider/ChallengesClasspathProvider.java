package dev.abarmin.provider.base.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.provider.base.domain.Challenge;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ChallengesClasspathProvider implements ChallengesProvider {
  @Autowired
  private ObjectMapper objectMapper;

  @Value("${challenges.classpath.resource}")
  private Resource challengesResource;

  private List<Challenge> challenges;

  @PostConstruct
  public void run() throws Exception {
    try (final InputStream stream = challengesResource.getInputStream()) {
      challenges = objectMapper.readerFor(Challenge.class)
          .<Challenge>readValues(stream)
          .readAll();
    }
  }

  @Override
  public Collection<Challenge> provide(int number) {
    if (number > challenges.size()) {
      throw new IllegalArgumentException(String.format(
          "Can't provide %s challenges, only %s available",
          number, challenges.size()
      ));
    }
    if (number <= 0) {
      throw new IllegalArgumentException(String.format(
          "Number of challenges should be positive, %s given",
          number
      ));
    }
    return challenges.subList(0, number);
  }
}
