package main;

import main.creating_thread_executor.Server;
import main.creating_thread_executor.Task;
import main.executors_that_returns_result.FactorialCalculator;
import main.processing_all_task_from_multiple.Result;
import main.processing_first_task_from_multiple.TaskValidator;
import main.processing_first_task_from_multiple.UserValidator;
import main.running_task_in_executor_periodically.PeriodicTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by G.Chalauri on 3/23/2017.
 */
public class Main {

    public static void main(String[] args) {
        //creatingThreadExecutor();
        // executorThatRetursResultExample();
        //validatorExample();
        // processingAllTasks();
        //scheduledExecutorTasks();
        periodicTasksExample();
    }

    private static void periodicTasksExample() {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n", new Date());
        PeriodicTask task = new PeriodicTask("TASK");

        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task,
                1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.printf("Main: Delay: %d\n", result.
                    getDelay(TimeUnit.MILLISECONDS));

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Finished at: %s\n", new Date());
    }

    private static void scheduledExecutorTasks() {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("Main: Starting at: %s\n", new Date());
        for (int i = 0; i < 5; i++) {
            main.scheduled_executor_tasks.Task task = new main.scheduled_executor_tasks.Task("Task " + i);
            executor.schedule(task, i + 1, TimeUnit.SECONDS);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Ends at: %s\n", new Date());
    }

    private static void processingAllTasks() {
        ExecutorService executor = (ExecutorService) Executors.
                newCachedThreadPool();

        List<main.processing_all_task_from_multiple.Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            main.processing_all_task_from_multiple.Task task = new main.processing_all_task_from_multiple.Task(String.valueOf(i));
            taskList.add(task);
        }

        List<Future<Result>> resultList = null;

        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("Main: Printing the results");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName() + ": " + result.
                        getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validatorExample() {
        String username = "test";
        String password = "test";

        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");

        TaskValidator ldapTask = new TaskValidator(ldapValidator,
                username, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executor = (ExecutorService) Executors.
                newCachedThreadPool();
        String result;

        try {
            result = executor.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");

    }

    private static void executorThatRetursResultExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.
                newFixedThreadPool(2);

        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);

            FactorialCalculator calculator = new FactorialCalculator(number);

            Future<Integer> result = executor.submit(calculator);

            resultList.add(result);
        }

        do {
            System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());

            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.
                        isDone());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } while (executor.getCompletedTaskCount() < resultList.size());

        System.out.printf("Main: Results\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.printf("Main: Task %d: %d\n", i, number);
        }

        executor.shutdown();
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
