package com.ahmedmq.infonote.note;

import com.ahmedmq.infonote.user.User;

import java.time.LocalDateTime;

public class NoteServiceImpl implements NoteService {

  private final NoteRule noteRule;

  private final NoteRepositoryImpl noteRepository;

  public NoteServiceImpl(NoteRule noteRule, NoteRepositoryImpl noteRepository) {
    this.noteRule = noteRule;
    this.noteRepository = noteRepository;
  }

  @Override
  public Note createNote(CreateNoteRequest noteRequest, User user) {

    if (user == null) {
      throw new IllegalArgumentException("User Cannot be null");
    }

    if (!noteRule.allowCreateNote(user)) {
      throw new UnauthorizedException("Permission Denied");
    }

    Note insertNote = new Note();
    insertNote.setTitle(noteRequest.getTitle());
    insertNote.setContent(noteRequest.getContent());
    insertNote.setAttachment(noteRequest.getAttachment());
    insertNote.setAuthor(user.getName());
    insertNote.setCreatedAt(LocalDateTime.now());

    return noteRepository.save(insertNote);
  }

}
