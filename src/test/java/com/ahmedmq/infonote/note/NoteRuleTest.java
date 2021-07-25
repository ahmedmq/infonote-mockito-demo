package com.ahmedmq.infonote.note;

import com.ahmedmq.infonote.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoteRuleTest {

  // Class under test
  NoteRule noteRule;

  @BeforeEach
  void setup() {
    noteRule = new NoteRule();
  }

  /**
   * Given a user with read role
   * When I call a method to validate if the user can create a note
   * Then I should return false
   */
  @Test
  @DisplayName("Story-2")
  void shouldReturnFalseForReadRole() {
    User user = new User("test", "read");
    assertFalse(noteRule.allowCreateNote(user));
  }

  /**
   * Given a user with write role
   * When I call a method to validate if the user can create a note
   * Then I should return true
   */
  @Test
  @DisplayName("Story-3")
  void shouldReturnTrueForWriteRole() {
    User user = new User("admin", "write");
    assertTrue(noteRule.allowCreateNote(user));
  }
}
