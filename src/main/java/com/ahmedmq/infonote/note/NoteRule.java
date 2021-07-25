package com.ahmedmq.infonote.note;

import com.ahmedmq.infonote.user.User;

public class NoteRule {

  public boolean allowCreateNote(User user){
    return !user.getRole().equals("read");
  }

}
