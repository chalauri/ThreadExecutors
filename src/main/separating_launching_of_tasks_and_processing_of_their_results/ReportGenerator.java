package main.separating_launching_of_tasks_and_processing_of_their_results;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by G.Chalauri on 03/24/17.
 */
public class ReportGenerator implements Callable<String> {

    private String sender;
    private String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        try {
            Long duration=(long)(Math.random()*10);
            System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n",this.sender,this.title,duration);
                    TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ret=sender+": "+title;
        return ret;
    }
}
