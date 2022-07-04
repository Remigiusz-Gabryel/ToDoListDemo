package com.codeFirstProject.repositories;

import com.codeFirstProject.Task;
import com.codeFirstProject.context.FileContext;
import com.codeFirstProject.interfaces.TaskRepository;

import java.io.IOException;
import java.util.List;

public class ToDoTaskRepository implements TaskRepository {

    FileContext fileContext;
    ToDoTaskRepository(FileContext fileContext){
        this.fileContext = fileContext;
    }
    @Override
    public List<Task> getAll() throws IOException {
        return fileContext.getTasks();
    }

    @Override
    public Task get(int id) {
        return null;
    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }
}
