package com.ahmedmq.infonote.note;

public class NoteUtil {

  public static final String[] RESERVED_WORDS = {"Infonote", "ahmedmq"};

  public static Boolean validNoteTitle(String title){
    boolean retValue = true;
    for (String s: RESERVED_WORDS){
      if (title.contains(s)) {
        retValue = false;
        break;
      }
    }

    if (title.length() <= 1) retValue = false;

    return retValue;
  }

}
