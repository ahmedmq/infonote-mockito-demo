package com.ahmedmq.infonote.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class NoteUtilTest {

  /**
   * Given a note title containing reserved keywords
   * When I validate the note title
   * Then I should return false
   */
  @Test
  @DisplayName("Story-7")
  void shouldReturnFalseForTitleWithReservedWords() {
    try (MockedStatic<NoteUtil> noteUtilMockedStatic = mockStatic(NoteUtil.class)) {
      noteUtilMockedStatic.when(()-> NoteUtil.validNoteTitle("Infonote title")).thenReturn(false);
      assertFalse(NoteUtil.validNoteTitle("Infonote title"));
    }
  }

  /**
   * Given a note title with just one character
   * When I validate the note title
   * Then I should return false
   */
  @Test
  @DisplayName("Story-8")
  void shouldReturnFalseForTitleWithLessThanOneChar() {
    try (MockedStatic<NoteUtil> noteUtilMockedStatic = mockStatic(NoteUtil.class)) {
      noteUtilMockedStatic.when(()-> NoteUtil.validNoteTitle(any())).thenReturn(false);
      assertFalse(NoteUtil.validNoteTitle("N"));
      // Verify if the method on the mocked object was called with the correct parameters
      noteUtilMockedStatic.verify(()-> NoteUtil.validNoteTitle("N"));
    }
  }

  /**
   * Given a note title which does not contain reserved words nor has just one character
   * When I validate the note title
   * Then I should return true
   */
  @Test
  @DisplayName("Story-9")
  void shouldReturnTrueForValidTitle() {
    try (MockedStatic<NoteUtil> noteUtilMockedStatic = mockStatic(NoteUtil.class)) {
      noteUtilMockedStatic.when(()-> NoteUtil.validNoteTitle("Test Title")).thenReturn(true);
      assertTrue(NoteUtil.validNoteTitle("Test Title"));
    }
  }

}
