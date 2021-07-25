package com.ahmedmq.infonote.note;

import com.ahmedmq.infonote.user.User;

public interface NoteService {

  Note createNote(CreateNoteRequest noteRequest, User user);

}
