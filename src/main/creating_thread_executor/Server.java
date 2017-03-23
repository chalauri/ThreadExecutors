package main.creating_thread_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by G.Chalauri on 3/23/2017.
 */

/*
Server class that will execute every task it receives using an
executor.
 */
public class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        /*
        If you send more tasks than the number of threads, the remaining tasks will be blocked until
        there is a free thread to process them This method receives the maximum number of threads
        as a parameter you want to have in your executor. In your case, you have created an executor
        with five threads.
         */
        //executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);
        System.out.printf("Server: Pool Size: %d\n", executor.
                getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.
                getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.
                getCompletedTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }
}
