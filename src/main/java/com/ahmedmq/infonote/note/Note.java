package com.ahmedmq.infonote.note;

import java.time.LocalDateTime;

public class Note {
  Long id;
  String title;
  String content;
  Boolean attachment;
  String author;
  LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Boolean getAttachment() {
    return attachment;
  }

  public void setAttachment(Boolean attachment) {
    this.attachment = attachment;
  }
}
