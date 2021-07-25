package com.ahmedmq.infonote.note;

public class NoteRepositoryImpl implements NoteRepository {
  @Override
  public Note save(Note note) {
    System.out.println("Saving Note into repository...");
    return note;
  }
}
