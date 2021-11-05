package dev.abarmin.provider.base.provider;

import dev.abarmin.provider.base.domain.Challenge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    ChallengesClasspathProvider.class,
    JacksonAutoConfiguration.class
})
@TestPropertySource(properties = {
    "challenges.classpath.resource=classpath:/test.challenges.json"
})
class ChallengesClasspathProviderTest {
  @Autowired
  private ChallengesClasspathProvider uut;

  @Test
  void check_contextStarts() {
    assertNotNull(uut);
  }

  @Test
  void provide_returnRequestNumberOfQuestions() {
    final Collection<Challenge> provided = uut.provide(2);

    assertEquals(2, provided.size());
  }

  @Test
  void provide_shouldThrowExceptionIfNegativeIsRequested() {
    assertThrows(IllegalArgumentException.class, () -> uut.provide(-1));
  }
}