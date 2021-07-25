package com.ahmedmq.infonote.note;

public class CreateNoteRequest {
  String title;
  String content;
  Boolean attachment;

  public CreateNoteRequest(String title){
    this(title,null,false);
  }

  public CreateNoteRequest(String title, String content, Boolean attachment) {
    this.title = title;
    this.content = content;
    this.attachment = attachment;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getAttachment() {
    return attachment;
  }

  public void setAttachment(Boolean attachment) {
    this.attachment = attachment;
  }
}
