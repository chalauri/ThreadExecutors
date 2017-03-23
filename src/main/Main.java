package main;

import main.creating_thread_executor.Server;
import main.creating_thread_executor.Task;

/**
 * Created by G.Chalauri on 3/23/2017.
 */
public class Main {
    public static void main(String[] args) {
        creatingThreadExecutor();
    }

    private static void creatingThreadExecutor() {
        Server server = new Server();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
