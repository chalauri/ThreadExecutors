package main.separating_launching_of_tasks_and_processing_of_their_results;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                // poll() Retrieves and removes the Future representing the next completed task, waiting if necessary up to the specified wait time if none are yet present.
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (result != null) {
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n", report);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender: End\n");
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

}
