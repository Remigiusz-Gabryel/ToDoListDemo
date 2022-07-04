package com.codeFirstProject.context;

import com.codeFirstProject.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileContext {
    private List<Task> tasks;
    private File contextDirectory;
    private String contextDirectoryAbsolutePath;

    public FileContext() {
    }

    public FileContext(File contextDirectory) {
        this.contextDirectory = contextDirectory;
        this.contextDirectoryAbsolutePath = contextDirectory.getAbsolutePath();
    }

    public void add(Task task) throws IOException {
        File newTask = new File(contextDirectoryAbsolutePath + File.separator + task.getTitle() + ".txt");

        if (!newTask.createNewFile()){
            return;
        }

        System.out.println("Task file created: " + newTask.getName());
        try (FileWriter fileWriter = new FileWriter(newTask)){

            fileWriter.write(task.getPriority() + "\n");
            fileWriter.write(task.isDone() + "\n");
            fileWriter.write(task.getDescription() + "\n");
        }
    }

    public void update(Task task) throws IOException{

        File taskToUpdate = new File(  contextDirectoryAbsolutePath + File.separator + task.getTitle() + ".txt");
        try (PrintWriter writer = new PrintWriter(taskToUpdate)){

            writer.write(task.getPriority() + "\n");
            writer.write(task.isDone() + "\n");
            writer.write(task.getDescription() + "\n");

        }
        File newTaskTitle = new File( contextDirectoryAbsolutePath + File.separator + task.getTitle() + ".txt");
        taskToUpdate.renameTo(newTaskTitle);
    }

    public void delete(Task task){

        File fileToDelete = new File(contextDirectoryAbsolutePath + File.separator + task.getTitle() + ".txt");
        fileToDelete.delete();

    }
    private void updateTaskList() throws IOException{
        String line;
        List<Task> taskToDo = new ArrayList<>();


        for (String path :
                contextDirectory.list()) {

            File taskFile = new File(contextDirectoryAbsolutePath + File.separator + path);
            Task task = new Task();
            boolean isSet = false;
            String fileName = taskFile.getName();


            try (Scanner scanner = new Scanner(taskFile)) {
                while (scanner.hasNextLine()) {

                    boolean isInt = scanner.hasNextInt();
                    line = scanner.nextLine();


                    task.setTitle(fileName.substring(0, fileName.indexOf(".")));

                    if ("true".equals(line)) {
                        task.setDone(Boolean.parseBoolean(line));
                    } else if (isInt && !isSet) {
                        task.setPriority(Integer.parseInt(line));
                        isSet = true;
                    } else {
                        task.setDescription(line);
                    }

                }
                taskToDo.add(task);
            }
        }
        tasks = taskToDo;

    }

    public File getContextDirectory() {
        return contextDirectory;
    }

    public void setContextDirectory(File contextDirectory) {
        this.contextDirectory = contextDirectory;
        this.contextDirectoryAbsolutePath = contextDirectory.getAbsolutePath();
    }

    public List<Task> getTasks() throws IOException{
        updateTaskList();
        return tasks;
    }
}
