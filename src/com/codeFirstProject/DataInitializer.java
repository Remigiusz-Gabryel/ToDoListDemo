package com.codeFirstProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {
    private  String toDoListDirectory;
    private final String DEFAULTTODOLISTDIRECTORY = "ToDoList";
    private  List<String> toDoFiles;
    private File toDoListFile;

    public DataInitializer() {
        this.toDoListDirectory = DEFAULTTODOLISTDIRECTORY;
//        this.toDoFiles = loadToDoList();
    }

   public void initializeToDoList() {

        toDoListFile = new File(toDoListDirectory);

        if (!toDoListFile.isDirectory()){
            System.out.println("Directory does not exist, checking for default ToDoList Directory...");
            toDoListDirectory = DEFAULTTODOLISTDIRECTORY;
            toDoListFile = new File(DEFAULTTODOLISTDIRECTORY);
        }

        List<String> taskFiles = new ArrayList<>();

        if (toDoListFile.mkdir()){
            System.out.println("Directory was not found, created new default directory");
            System.out.println("There is no task for you :)");
            System.out.println();
        } else{
            System.out.println("Directory exists, load Todo List: ");
            System.out.println();
            taskFiles = Arrays.asList(toDoListFile.list());

            if (taskFiles.size() == 0){
                System.out.println("List is empty");
                System.out.println();
            }

        }
        toDoFiles = taskFiles;
    }


    public String getToDoListDirectory() {
        return toDoListDirectory;
    }

    public void setToDoListDirectory(String toDoListDirectory) {
        this.toDoListDirectory = toDoListDirectory;
    }

    public List<String> getToDoFiles() {
        toDoFiles = Arrays.asList(toDoListFile.list());
        return toDoFiles;
    }
}
