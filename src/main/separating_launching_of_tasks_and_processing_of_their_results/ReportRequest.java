package main.separating_launching_of_tasks_and_processing_of_their_results;

import java.util.concurrent.CompletionService;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class ReportRequest implements Runnable {

    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String>
            service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,
                "Report");
        service.submit(reportGenerator);
    }
}
