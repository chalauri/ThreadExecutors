package main.running_task_in_executor_periodically;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class PeriodicTask implements Runnable {

    private String name;

    public PeriodicTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        System.out.printf("%s: Hello, world : %s\n", name, new Date());
    }
}
