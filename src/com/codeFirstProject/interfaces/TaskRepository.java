package com.codeFirstProject.interfaces;

import com.codeFirstProject.Task;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    List<Task> getAll() throws IOException;
    Task get(int id);
    void create(Task task);
    void update(Task task);
    void delete(Task task);
}
