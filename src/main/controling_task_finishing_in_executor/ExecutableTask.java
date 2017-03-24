package main.controling_task_finishing_in_executor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class ExecutableTask implements Callable<String> {

    private String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
        }
        return "Hello, world. I'm " + name;
    }
}
