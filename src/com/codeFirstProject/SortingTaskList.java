package com.codeFirstProject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTaskList {
    public static List<Task> sortByPriority(List<Task> toDoList) {
        return toDoList.stream().
                sorted(Comparator.comparingInt(Task::getPriority))
                .collect(Collectors.toList());
    }
    //    public void displayTasksByStatus(boolean isDone) {
//        listOfTask.stream().map(t -> t.isDone() == isDone).forEach(System.out::println);
//    }
}
