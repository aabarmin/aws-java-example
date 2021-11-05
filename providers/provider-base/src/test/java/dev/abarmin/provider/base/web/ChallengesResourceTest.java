package dev.abarmin.provider.base.web;

import dev.abarmin.provider.base.domain.Challenge;
import dev.abarmin.provider.base.domain.ChallengeAnswer;
import dev.abarmin.provider.base.provider.ChallengesProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChallengesResource.class)
class ChallengesResourceTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChallengesProvider challengesProvider;

  @Test
  void perform_whenSendingRequestProviderShouldBeInvoked() throws Exception {
    final String requestContent = """
        {
            "discipline": "math",
            "number": 10
        }
        """;

    mockMvc.perform(
            post("/provide")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
        )
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    verify(challengesProvider, times(1)).provide(eq(10));
  }

  @Test
  void perform_whenSendingRequestResponseShouldBeGenerated() throws Exception {
    when(challengesProvider.provide(anyInt())).thenAnswer(inv ->
        IntStream.range(0, inv.getArgument(0))
            .mapToObj(this::createChallenge)
            .collect(Collectors.toList()));

    final String requestContent = """
        {
          "discipline": "math",
          "number": 10
        }
        """;

    mockMvc.perform(
        post("/provide")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestContent)
    )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.discipline").value("math"))
        .andExpect(jsonPath("$.challenges").isArray());
  }

  private Challenge createChallenge(int index) {
    final Challenge challenge = new Challenge();
    challenge.setQuestion("Question " + index);
    challenge.setAnswers(
        IntStream.range(0, 4)
            .mapToObj(answerIndex -> createAnswer(answerIndex, answerIndex == 1))
            .collect(Collectors.toList())
    );
    return challenge;
  }

  private ChallengeAnswer createAnswer(int index, boolean correct) {
    final ChallengeAnswer answer = new ChallengeAnswer();
    answer.setAnswer("Answer " + index);
    answer.setCorrect(correct);
    return answer;
  }
}