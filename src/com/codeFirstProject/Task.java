package com.codeFirstProject;

public class Task {
   private String title;
   private int priority;
   private boolean isDone;
   private String description;

   public Task() {}

   public Task(String title, int priority, boolean isDone, String description) {
      this.title = title;
      this.description = description;
      this.priority = priority;
      this.isDone = isDone;
   }

   @Override
   public String toString() {
      return "Task{" +
              "title='" + title + '\'' +
              ", priority=" + priority +
              ", isDone=" + isDone +
              ", description='" + description + '\'' +
              '}';
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getPriority() {
      return priority;
   }

   public void setPriority(int priority) {
      this.priority = priority;
   }

   public boolean isDone() {
      return isDone;
   }

   public void setDone(boolean done) {
      isDone = done;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
