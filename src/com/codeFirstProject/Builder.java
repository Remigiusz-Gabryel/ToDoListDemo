package com.codeFirstProject;

import com.codeFirstProject.context.FileContext;
import com.codeFirstProject.interfaces.TaskService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder {
    private  String toDoListDirectoryPath;
    private TaskService taskService;
    private final String DEFAULT_TO_DO_CONTEXT_DIRECTORY = "ToDoList";
    private  List<String> toDoFiles;
    private File toDoListFileDirectory;
    private FileContext fileContext;

    public Builder() {
        this.toDoListDirectoryPath = DEFAULT_TO_DO_CONTEXT_DIRECTORY;
//        this.toDoFiles = loadToDoList();
    }

    public Builder(String toDoListDirectoryPath) {
        this.toDoListDirectoryPath = toDoListDirectoryPath;
    }




    /**
     * Checking if there is an existing directory with provided path in constructor,
     * if default constructor used or directory with given path does not exist,
     * checking if default directory exist.
     * @return Returning existing default directory
     * or create default and returning it.
     *
     * Default directory is located in program directory.
     *
     * Default directory is ToDoList
     *
     * */
    public void UseToDoListFileContext() {

        toDoListFileDirectory = new File(toDoListDirectoryPath);

        if (!toDoListFileDirectory.isDirectory()){
            System.out.println("Provided path is not a directory or does not exist.");
            System.out.println("Checking for default ToDoList Directory...");
            toDoListDirectoryPath = DEFAULT_TO_DO_CONTEXT_DIRECTORY;
            toDoListFileDirectory = new File(DEFAULT_TO_DO_CONTEXT_DIRECTORY);
        }

        List<String> taskFiles = new ArrayList<>();

        if (toDoListFileDirectory.mkdir()){
            System.out.println("Directory was not found, created new default directory");
            System.out.println("There is no task for you :)");
            System.out.println();
        } else{
            System.out.println("Directory exists, load Todo List: ");
            System.out.println();
            taskFiles = Arrays.asList(toDoListFileDirectory.list());

            if (taskFiles.size() == 0){
                System.out.println("List is empty");
                System.out.println();
            }

        }
        toDoFiles = taskFiles;
        fileContext.setContextDirectory(toDoListFileDirectory);
    }


    public String getToDoListDirectoryPath() {
        return toDoListDirectoryPath;
    }

    public void setToDoListDirectoryPath(String toDoListDirectoryPath) {
        this.toDoListDirectoryPath = toDoListDirectoryPath;
    }

    public List<String> getToDoFiles() {
        toDoFiles = Arrays.asList(toDoListFileDirectory.list());
        return toDoFiles;
    }

    public FileContext getFileContext() {
        return fileContext;
    }

    public void setFileContext(FileContext fileContext) {
        this.fileContext = fileContext;
    }

    public TaskService getTaskService() {
        return taskService;
}

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
