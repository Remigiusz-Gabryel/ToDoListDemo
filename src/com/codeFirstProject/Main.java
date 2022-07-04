package com.codeFirstProject;

import com.codeFirstProject.util.ScannerHelperMethods;
import com.codeFirstProject.util.StandardMessages;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {



            ToDoListApp toDoListApp = new ToDoListApp(scanner);


            toDoListApp.run();
            System.out.println();







        }

    }
}
