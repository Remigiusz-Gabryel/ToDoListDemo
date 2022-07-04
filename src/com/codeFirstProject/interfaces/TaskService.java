package com.codeFirstProject.interfaces;

import com.codeFirstProject.Task;

import java.io.IOException;
import java.util.List;

public interface TaskService {
    List<Task> getAll() throws IOException;
    Task get(int id);
    void create(String path) throws IOException;
    void delete(int index);
    void update(int index) throws IOException;

    List<Task> getToDoTasks();

    void setToDoTasks(List<Task> toDoTasks);
}
