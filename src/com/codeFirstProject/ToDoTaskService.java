package com.codeFirstProject;

import com.codeFirstProject.interfaces.TaskService;
import com.codeFirstProject.util.ScannerHelperMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoTaskService implements TaskService {
    private List<Task> toDoTasks;
    private Scanner scanner;
    private DataInitializer dataInitializer;

    public ToDoTaskService(Scanner scanner,DataInitializer dataInitializer) throws IOException {
        this.scanner = scanner;
        this.dataInitializer = dataInitializer;
        toDoTasks = getAll();
    }

    @Override
    public List<Task> getAll() throws IOException{
        String line;
        List<Task> taskToDo = new ArrayList<>();


        for (String path :
                dataInitializer.getToDoFiles()) {

            File taskFile = new File(dataInitializer.getToDoListDirectory() + File.separator + path);
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
        toDoTasks = taskToDo;
        return toDoTasks;
    }

    @Override
    public Task get(int id) {
        return toDoTasks.get(id);
    }

    @Override
    public void create(String path) throws IOException {
        Task task = new Task();

        task.setTitle(getTaskTitleFromUser(scanner));
        task.setPriority(getTaskPriorityFromUser(scanner));
        task.setDone(false);
        task.setDescription(getTaskDescriptionFromUser(scanner));
        toDoTasks.add(task);
        createTaskFile(task, path);

    }

    @Override
    public void delete(int index) {
        Task toDelete = toDoTasks.get(index);
        File fileToDelete = new File(dataInitializer.getToDoListDirectory() + File.separator + toDelete.getTitle() + ".txt");

        fileToDelete.delete();
        toDoTasks.remove(index);

    }

    @Override
    public void update(int index) throws IOException{
    Task task = toDoTasks.get(index);
    File taskToUpdate = new File( dataInitializer.getToDoListDirectory() + File.separator + task.getTitle() + ".txt");
    try (PrintWriter writer = new PrintWriter(taskToUpdate)){
        task.setTitle(getTaskTitleFromUser(scanner));
        task.setPriority(getTaskPriorityFromUser(scanner));
        task.setDone(false);
        task.setDescription(getTaskDescriptionFromUser(scanner));

        writer.write(task.getPriority() + "\n");
        writer.write(task.isDone() + "\n");
        writer.write(task.getDescription() + "\n");

    }
        File newTaskTitle = new File( dataInitializer.getToDoListDirectory() + File.separator + task.getTitle() + ".txt");
        taskToUpdate.renameTo(newTaskTitle);

    }
    private void createTaskFile(Task task, String path) throws IOException {

        File newTask = new File(path + File.separator + task.getTitle() + ".txt");

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

    private String getTaskDescriptionFromUser(Scanner scanner) {

            System.out.println("Set Description (only letters and numbers are allowed)");
            return ScannerHelperMethods.allowOnlyAlphabetValuesAndNumbers(scanner);

    }

    private String getTaskTitleFromUser(Scanner scanner) {

            System.out.println("Set Title(only letters are allowed)");
            return ScannerHelperMethods.allowOnlyAlphabetValues(scanner);

    }

    private int getTaskPriorityFromUser(Scanner scanner) {

            System.out.println("Set Priority (Only numbers from 1 - 10 inclusive)");
            return ScannerHelperMethods.getInt(scanner, 1, 10);

    }
    @Override
    public List<Task> getToDoTasks() {
        return toDoTasks;
    }

    @Override
    public void setToDoTasks(List<Task> toDoTasks) {
        this.toDoTasks = toDoTasks;
    }
}
