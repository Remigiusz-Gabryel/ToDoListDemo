package com.codeFirstProject;

import com.codeFirstProject.interfaces.TaskService;
import com.codeFirstProject.util.ScannerHelperMethods;
import com.codeFirstProject.util.StandardMessages;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    Scanner scanner;
    private TaskService taskService;
    private Builder builder;
    private ToDoListManager toDoListManager;

    public ToDoListApp(Scanner scanner, Builder builder) {
        this.builder = builder;
        this.scanner = scanner;
    }

    public void run() {
//        toDoListApp.setDataInitializer(new DataInitializer());


            try {
                AddToDoAppContext();

                mainMenuActions();


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }



    }

    private void AddToDoAppContext() throws IOException {
        System.out.println("Provide path to ToDoList");
        String toDoListPath = scanner.nextLine();
        builder.setToDoListDirectoryPath(toDoListPath);
        builder.UseToDoListFileContext();
        taskService = new ToDoTaskService(scanner, builder);
        toDoListManager = new ToDoListManager(builder,taskService);

    }

    private void mainMenuActions() throws IOException {
        int userChoice;

        toDoListManager.displayTasks();
        StandardMessages.displayMenu();
         userChoice = ScannerHelperMethods.getInt(scanner,1,5);

         if (userChoice == 1){
             createTaskAction();
         } else if( userChoice == 2){
            updateTaskAction();
         } else if( userChoice == 3){
            removeTaskAction();
         } else if( userChoice == 4){
            sortingTaskList();
         } else if( userChoice == 5){
             System.out.println("App Closing BYE!");
            System.exit(0);
         }


    }

    private void sortingTaskList() throws IOException{
        int userChoice;
        StandardMessages.displaySortMenu();
        userChoice = ScannerHelperMethods.getInt(scanner,1,1);

        if (userChoice == 1){
        List<Task> sorted = SortingTaskList.sortByPriority(taskService.getAll());
        taskService.setToDoTasks(sorted);
        toDoListManager.setListOfTask(sorted);
        mainMenuActions();
        } else if( userChoice == 2){
           mainMenuActions();
        }
    }
    private void removeTaskAction() throws IOException{
        System.out.println("Which task you want to remove ? Provide an index!: ");
//        scanner.nextLine();
        if (taskService.getToDoTasks().size() == 0){
            System.out.println("There are no task to remove ! ");
            System.out.println();
            mainMenuActions();
            return;
        }
        int taskToRemove = ScannerHelperMethods.getInt(scanner,0,taskService.getToDoTasks().size() - 1);
        taskService.delete(taskToRemove);
        mainMenuActions();
    }
    private void createTaskAction() throws IOException{
        scanner.nextLine();
        taskService.create(builder.getToDoListDirectoryPath());
        toDoListManager.setListOfTask(taskService.getAll());
        toDoListManager.displayTasks();
        mainMenuActions();
    }
    private void updateTaskAction() throws IOException{
        System.out.println("Provide task index to update: ");
        if (taskService.getToDoTasks().size() == 0){
            System.out.println("There are no task to update ");
            System.out.println();
            mainMenuActions();
            return;
        }
        int taskToUpdate = ScannerHelperMethods.getInt(scanner,0,taskService.getToDoTasks().size() - 1);
        taskService.edit(taskToUpdate);
        mainMenuActions();
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void setToDoListManager(ToDoListManager toDoListManager) {
        this.toDoListManager = toDoListManager;
    }
}
