package main.canceling_task_in_executor;

import java.util.concurrent.Callable;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class CancelTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }
}
