package com.codeFirstProject;

import com.codeFirstProject.context.FileContext;
import com.codeFirstProject.interfaces.TaskService;
import com.codeFirstProject.util.ScannerHelperMethods;
import com.codeFirstProject.util.StandardMessages;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            Builder builder = new Builder("ToDoList");
            builder.setFileContext(new FileContext());
            builder.setTaskService(new ToDoTaskService(scanner,builder));

            ToDoListApp toDoListApp = new ToDoListApp(scanner,builder);


            toDoListApp.run();
            System.out.println();







        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
