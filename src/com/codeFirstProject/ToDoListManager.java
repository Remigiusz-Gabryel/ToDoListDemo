package com.codeFirstProject;

import com.codeFirstProject.interfaces.TaskService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListManager {
    private List<String> taskFilesPaths;
    // Interface needed
    private Builder dataInitializer;
    private List<Task> listOfTask;
    private TaskService taskService;

    public ToDoListManager(Builder dataInitializer, TaskService taskService) throws IOException {
        this.dataInitializer = dataInitializer;
        this.taskService = taskService;
        this.taskFilesPaths = dataInitializer.getToDoFiles();
        listOfTask = getFileContent();
    }


    public void displayTasks() {
        listOfTask = taskService.getToDoTasks();
        if (!(listOfTask.size() == 0)) {
            System.out.println();
            System.out.println("List of task: ");
            listOfTask.forEach(System.out::println);
        }
    }


    private List<Task> getFileContent() throws IOException {
        String line;
        List<Task> taskToDo = new ArrayList<>();


        for (String path :
                taskFilesPaths) {

            File taskFile = new File(dataInitializer.getToDoListDirectoryPath() + File.separator + path);
            Task task = new Task();

            String fileName = taskFile.getName();


            try (Scanner scanner = new Scanner(taskFile)) {
                while (scanner.hasNextLine()) {

                    boolean isInt = scanner.hasNextInt();
                    line = scanner.nextLine();


                    task.setTitle(fileName.substring(0, fileName.indexOf(".")));

                    if ("true".equals(line)) {
                        task.setDone(Boolean.parseBoolean(line));
                    } else if (isInt) {
                        task.setPriority(Integer.parseInt(line));
                    } else {
                        task.setDescription(line);
                    }

                }
                taskToDo.add(task);
            }
        }
        return taskToDo;
    }

    public void setListOfTask(List<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }

    public List<Task> getListOfTask(){
        return listOfTask;
    }

}
