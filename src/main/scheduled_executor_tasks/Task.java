package main.scheduled_executor_tasks;

import java.util.Date;
import java.util.StringJoiner;
import java.util.concurrent.Callable;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class Task implements Callable<String> {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n",name,new Date());
        return "Hello, world";
    }
}
