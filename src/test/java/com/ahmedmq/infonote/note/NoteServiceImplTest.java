package com.ahmedmq.infonote.note;

import com.ahmedmq.infonote.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {


  public static final User READ_ROLE_USER = new User("test", "read");
  public static final User WRITE_ROLE_USER = new User("admin", "write");
  public static final CreateNoteRequest TEST_NOTE = new CreateNoteRequest("Title", "Content", false);
  public static final LocalDateTime TEST_LOCAL_DATETIME = LocalDateTime.of(2020, 1, 1, 12, 0);
  public static final long TEST_NOTE_ID = 99L;

  @Mock
  NoteRule noteRule;

  @Mock
  NoteRepositoryImpl noteRepositoryImpl;

  /**
   * ArgumentCaptor used to verify if we are passing the right argument to a function.
   */
  @Captor
  ArgumentCaptor<Note> noteArgumentCaptor;

  // Class Under Test
  @InjectMocks
  NoteServiceImpl noteServiceImpl;

  /**
   * Given user details are unknown or missing
   * When I create a note
   * Then I should see an error message as IllegalArgumentException
   */
  @Test
  @DisplayName("Story-1")
  void shouldThrowErrorForMissingUser() {
    // Both arguments can be null as only we are testing for a null user argument
    assertThrows(IllegalArgumentException.class, () -> noteServiceImpl.createNote(null, null));
  }


  /**
   * Given a user with 'read' role
   * when I create a note
   * Then I should see an error message as UnauthorizedException
   */
  @Test
  @DisplayName("Story-4")
  void shouldThrowErrorForReadRoleWhenCreatingNote() {
    assertThrows(UnauthorizedException.class, () -> noteServiceImpl.createNote(null, READ_ROLE_USER));
  }

  /**
   * Given a user with 'write' role
   * when I create a note
   * Then I should be able to create a note successfully
   */
  @Test
  @DisplayName("Story-5")
  void shouldCreateNoteSuccessfullyForRegisteredUser() {
    when(noteRule.allowCreateNote(WRITE_ROLE_USER)).thenReturn(true);
    // Simply return a new note, we anyway are asserting for null condition
    when(noteRepositoryImpl.save(any())).thenReturn(new Note());

    Note actualNote = noteServiceImpl.createNote(TEST_NOTE, WRITE_ROLE_USER);

    assertNotNull(actualNote);
  }


  /**
   * Given a user with 'write' role
   * when I create a note
   * Then I should be able to create a note successfully with `Author`, `createdAt`  fields
   */
  @Test
  @DisplayName("Story-6")
  void shouldStoreNoteWithAllCorrectValues() {
    // Mock the `allowCreateNote` in the noteRule object method to return true when called
    when(noteRule.allowCreateNote(WRITE_ROLE_USER)).thenReturn(true);
    // Mock the save method in the `noteRepositoryImpl` object to return a Note class with values assigned for `author` and `createdAt`
    when(noteRepositoryImpl.save(any(Note.class))).thenAnswer(invocationOnMock -> {
      Note toSaveNote = invocationOnMock.getArgument(0);
      toSaveNote.setId(99L);
      toSaveNote.setCreatedAt(TEST_LOCAL_DATETIME);
      return toSaveNote;
    });

    noteServiceImpl.createNote(TEST_NOTE,WRITE_ROLE_USER);
    // Use the Mock verify method to capture the input Note argument to the `save` method
    verify(noteRepositoryImpl).save(noteArgumentCaptor.capture());
    Note insertNote = noteArgumentCaptor.getValue();

    assertEquals(TEST_NOTE.getTitle(), insertNote.getTitle());
    assertEquals(TEST_NOTE.getContent(), insertNote.getContent());
    assertEquals(TEST_NOTE.getAttachment(), insertNote.getAttachment());
    assertEquals(TEST_NOTE_ID, insertNote.getId());
    assertEquals(TEST_LOCAL_DATETIME, insertNote.getCreatedAt());
  }
}
